package cmput301f17t12.quirks.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import cmput301f17t12.quirks.Controllers.ElasticSearchUserController;
import cmput301f17t12.quirks.Helpers.HelperFunctions;
import cmput301f17t12.quirks.Models.Event;
import cmput301f17t12.quirks.Models.EventList;
import cmput301f17t12.quirks.Models.Quirk;
import cmput301f17t12.quirks.Models.QuirkList;
import cmput301f17t12.quirks.Models.User;
import cmput301f17t12.quirks.R;

public class NewEventActivity extends BaseActivity {

    private static final int SELECTED_PICTURE = 0;
    private Bitmap bitmap;
    private User currentlylogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Spinner dropdown = (Spinner) findViewById(R.id.quirk_dropdown);

        SharedPreferences settings = getSharedPreferences("dbSettings", Context.MODE_PRIVATE);
        String jestID = settings.getString("jestID", "defaultvalue");

        if (jestID.equals("defaultvalue")) {
            Log.i("Error", "Did not find correct jestID");
        }

        currentlylogged = HelperFunctions.getUserObject(jestID);

        if (currentlylogged != null) {
            QuirkList quirks = currentlylogged.getQuirks();
            ArrayAdapter<Quirk> adapter = new ArrayAdapter<Quirk>(this, android.R.layout.simple_spinner_item, quirks.getList());
            dropdown.setAdapter(adapter);

            Button savebutton = (Button) findViewById(R.id.save_button);
            TextView errormsg = (TextView) findViewById(R.id.errormsg);

            if (quirks.getList().isEmpty()) {
                savebutton.setEnabled(false);
                errormsg.setVisibility(View.VISIBLE);
            }
            else {
                savebutton.setEnabled(true);
                errormsg.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void pickPhoto(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECTED_PICTURE);
    }

    public void removePhoto(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(0);
        bitmap = null;
    }

    public void saveCommand(View view) {
        Spinner dropdown = (Spinner) findViewById(R.id.quirk_dropdown);
        EditText commentText = (EditText) findViewById(R.id.comment_edittext);

        Quirk selectedQuirk = (Quirk) dropdown.getSelectedItem();
        selectedQuirk.incCurrValue();
        String comment = commentText.getText().toString();

        byte[] photoByte = new byte[] {};
        if (photoByte.length != 0) {
            photoByte = bitmapToByte(bitmap);
        }

        EventList events = selectedQuirk.getEventList();
        Event newEvent = new Event(currentlylogged.getUsername(), comment, photoByte, new Date());
        events.addEvent(newEvent);

        ElasticSearchUserController.UpdateUserTask updateUserTask
                = new ElasticSearchUserController.UpdateUserTask();
        updateUserTask.execute(currentlylogged);

        startActivity(new Intent(this, MainActivity.class));
    }

    public void cancelCommand(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECTED_PICTURE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri photouri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), photouri);
                setImage(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setImage(Bitmap photo) {
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(photo);

        Button savebutton = (Button) findViewById(R.id.save_button);
        TextView errormsg = (TextView) findViewById(R.id.errormsg);
        if (bitmapToByte(photo).length >= 65536) {
            savebutton.setEnabled(false);
            errormsg.setVisibility(View.VISIBLE);
            errormsg.setText("Photo size is too big!");
        } else {
            savebutton.setEnabled(true);
            errormsg.setVisibility(View.INVISIBLE);
        }
    }

    public byte[] bitmapToByte(Bitmap bmp) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_new_event;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_newevent;
    }
}
