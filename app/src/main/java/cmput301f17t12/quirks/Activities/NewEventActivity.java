package cmput301f17t12.quirks.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;

import cmput301f17t12.quirks.Controllers.ElasticSearchUserController;
import cmput301f17t12.quirks.Models.Inventory;
import cmput301f17t12.quirks.Models.Quirk;
import cmput301f17t12.quirks.Models.QuirkList;
import cmput301f17t12.quirks.Models.User;
import cmput301f17t12.quirks.R;

public class NewEventActivity extends BaseActivity {

    private static final int SELECTED_PICTURE = 0;
    private String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Spinner dropdown = (Spinner)findViewById(R.id.quirk_dropdown);

        // Testing - replace this with a userID search later on.
        String username = "testing123";

        String query = "{" +
                "  \"query\": {" +
                "    \"match\": {" +
                "      \"username\": \"" + username + "\"" +
                "    }" +
                "  }" +
                "}";

        ElasticSearchUserController.GetUsersTask getUsersTask
                = new ElasticSearchUserController.GetUsersTask();
        getUsersTask.execute(query);

        try {

            ArrayList<User> users = getUsersTask.get();
            User currentlylogged = users.get(0);


            Log.d("testing", users.toString());
            QuirkList events = currentlylogged.getQuirks();
            ArrayAdapter<Quirk> adapter = new ArrayAdapter<Quirk> (this, android.R.layout.simple_spinner_item, events.getList());

            dropdown.setAdapter(adapter);
        }
        catch (Exception e) {
            Log.i("Error", "Failed to get the users from the async object");
            Log.i("Error", e.toString());
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
        photoPath = "";
    }

    public void saveCommand(View view) {
        // TODO: Upload to database pass intent
        Spinner dropdown = (Spinner) findViewById(R.id.quirk_dropdown);
        EditText commentText = (EditText) findViewById(R.id.comment_edittext);

        Quirk selectedQuirk = (Quirk) dropdown.getSelectedItem();
        String comment = commentText.getText().toString();

        Log.d("testing", selectedQuirk.toString());
        Log.d("testing", comment);
        Log.d("testing", photoPath);

    }

    public void cancelCommand(View view) {
        startActivity(new Intent(this, MainActivity.class));
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

                photoPath = getRealPathFromURI(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
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
