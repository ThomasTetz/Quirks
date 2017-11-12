package cmput301f17t12.quirks.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import cmput301f17t12.quirks.Controllers.ElasticSearchUserController;
import cmput301f17t12.quirks.Helpers.HelperFunctions;
import cmput301f17t12.quirks.Models.Event;
import cmput301f17t12.quirks.Models.EventList;
import cmput301f17t12.quirks.Models.Quirk;
import cmput301f17t12.quirks.Models.QuirkList;
import cmput301f17t12.quirks.Models.User;
import cmput301f17t12.quirks.R;

public class EditEventActivity extends AppCompatActivity {

    private static final int SELECTED_PICTURE = 0;
    private String photoPath = "";
    private User currentlylogged;
    private Event referenced_event;
    private Event referenced_event_copy;
    private Quirk referenced_quirk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        // Testing - replace this with a userID search later on.
        String jestID = "AV-xx8ahi8-My2t7XP4j";

        currentlylogged = HelperFunctions.getUserObject(jestID);

        final EditText commentEdit = (EditText) findViewById(R.id.comment_edittext);

        Event selectedEvent = (Event) getIntent().getSerializableExtra("EDIT_EVENT");
        Quirk selectedQuirk = (Quirk) getIntent().getSerializableExtra("SELECTED_QUIRK");

        ArrayList<Quirk> quirklist = currentlylogged.getQuirks().getList();
        Quirk referenced_quirk = quirklist.get(quirklist.indexOf(selectedQuirk));
        ArrayList<Event> temp = referenced_quirk.getEventList().getList();
        Event referenced_event = temp.get(temp.indexOf(selectedEvent));
        referenced_event_copy = referenced_event;

        Uri imageUri = Uri.fromFile(new File(selectedEvent.getPhotoPath()));
        setImage(imageUri);
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
        photoPath = "";
    }

    public void saveCommand(View view) {
        Spinner dropdown = (Spinner) findViewById(R.id.quirk_dropdown);
        EditText commentText = (EditText) findViewById(R.id.comment_edittext);

        String comment = commentText.getText().toString();

        referenced_event.setComment(comment);
        referenced_event.setPhotoPath(photoPath);
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
        referenced_quirk.removeEvent(referenced_event);
        ElasticSearchUserController.UpdateUserTask updateUserTask
                = new ElasticSearchUserController.UpdateUserTask();
        updateUserTask.execute(currentlylogged);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECTED_PICTURE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            setImage(uri);
        }
    }

    private void setImage(Uri uri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageBitmap(bitmap);

            photoPath = getRealPathFromURI(uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }
}
