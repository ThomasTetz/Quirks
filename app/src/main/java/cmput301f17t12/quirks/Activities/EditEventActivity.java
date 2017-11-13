package cmput301f17t12.quirks.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import cmput301f17t12.quirks.Controllers.ElasticSearchUserController;
import cmput301f17t12.quirks.Helpers.HelperFunctions;
import cmput301f17t12.quirks.Models.Event;
import cmput301f17t12.quirks.Models.Quirk;
import cmput301f17t12.quirks.Models.User;
import cmput301f17t12.quirks.R;

public class EditEventActivity extends AppCompatActivity {

    private static final int SELECTED_PICTURE = 0;
    private User currentlylogged;
    Bitmap bitmap;
    private Event referenced_event;
    private Event referenced_event_copy;
    private Quirk referenced_quirk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        SharedPreferences settings = getSharedPreferences("dbSettings", Context.MODE_PRIVATE);
        String jestID = settings.getString("jestID", "defaultvalue");

        if (jestID.equals("defaultvalue")) {
            Log.i("Error", "Did not find correct jestID");
        }

        currentlylogged = HelperFunctions.getUserObject(jestID);

        final EditText commentEdit = (EditText) findViewById(R.id.comment_edittext);

        Event selectedEvent = (Event) getIntent().getSerializableExtra("EDIT_EVENT");
        Quirk selectedQuirk = (Quirk) getIntent().getSerializableExtra("SELECTED_QUIRK");
        String filepath = getIntent().getStringExtra("FILE_PATH");

        ArrayList<Quirk> quirklist = currentlylogged.getQuirks().getList();

//        referenced_quirk = quirklist.get(quirklist.indexOf(selectedQuirk));
//        ArrayList<Event> temp = referenced_quirk.getEventList().getList();
//        referenced_event = temp.get(temp.indexOf(selectedEvent));
//        referenced_event_copy = referenced_event;

        Log.d("testing", filepath);
        //loads the file
        if (!filepath.isEmpty()) {
            File file = new File(filepath);
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            setImage(bitmap);
            file.delete();
        }

        commentEdit.setText(selectedEvent.getComment());
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
        EditText commentText = (EditText) findViewById(R.id.comment_edittext);

        String comment = commentText.getText().toString();

        referenced_event.setComment(comment);
        byte[] photoByte;
        if (bitmap != null) {
            photoByte = bitmapToByte(bitmap);
        } else {
            photoByte = null;
        }

        referenced_event.setPhotoByte(photoByte);
        referenced_event.setDate(new Date());

        ElasticSearchUserController.UpdateUserTask updateUserTask
                = new ElasticSearchUserController.UpdateUserTask();
        updateUserTask.execute(currentlylogged);

        startActivity(new Intent(this, MainActivity.class));
    }

    public void cancelCommand(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void deleteCommand(View view) {
        referenced_quirk.removeEvent(referenced_event_copy);
        ElasticSearchUserController.UpdateUserTask updateUserTask
                = new ElasticSearchUserController.UpdateUserTask();
        updateUserTask.execute(currentlylogged);
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

            if (errormsg.getVisibility() == View.VISIBLE) {
                errormsg.setVisibility(View.INVISIBLE);
            }
        }
    }

    public byte[] bitmapToByte(Bitmap bmp) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
