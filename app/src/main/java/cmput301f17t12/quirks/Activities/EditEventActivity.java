package cmput301f17t12.quirks.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

import cmput301f17t12.quirks.Models.Event;
import cmput301f17t12.quirks.R;

public class EditEventActivity extends AppCompatActivity {

    private static final int SELECTED_PICTURE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        final EditText commentEdit = (EditText) findViewById(R.id.comment_edittext);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        // TODO: Load values of the selected counter into the UI
        final Event selectedCounter = (Event) getIntent().getSerializableExtra("EDIT_COUNTER");
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
    }

    public void saveCommand(View view) {
        // TODO: Upload to database pass intent
    }

    public void cancelCommand(View view) {
        finish();
    }

    public void deleteCommand(View view) {
        // TODO: remove from database
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECTED_PICTURE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
// TODO: get path and upload to database on save press
//    private Bitmap getPath(Uri uri) {
//
//        String[] projection = { MediaStore.Images.Media.DATA };
//        Cursor cursor = managedQuery(uri, projection, null, null, null);
//        int column_index = cursor
//                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        String filePath = cursor.getString(column_index);
//        cursor.close();
//        // Convert file path into bitmap image using below line.
//        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
//
//        return bitmap;
//    }
}
