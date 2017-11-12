package cmput301f17t12.quirks.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;

import cmput301f17t12.quirks.R;

public class NewEventActivity extends BaseActivity {

    private static final int SELECTED_PICTURE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Spinner dropdown = (Spinner)findViewById(R.id.quirk_dropdown);

        // TODO: Fill dropdown with list of quirks
        ArrayList<String> years = new ArrayList<String>();
        for (int i = 0; i <= 1500; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);

        dropdown.setAdapter(adapter);
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
        // Upload to database pass intent
    }

    public void cancelCommand(View view) {
        finish();
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

    @Override
    int getContentViewId() {
        return R.layout.activity_new_event;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_newevent;
    }
}
