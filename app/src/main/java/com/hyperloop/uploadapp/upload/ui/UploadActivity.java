package com.hyperloop.uploadapp.upload.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hyperloop.login.R;
import com.hyperloop.uploadapp.login.ui.LoginActivity;
import com.hyperloop.uploadapp.upload.Category;
import com.hyperloop.uploadapp.upload.UploadPresenter;
import com.hyperloop.uploadapp.upload.UploadPresenterImpl;
import com.hyperloop.uploadapp.upload.adapter.CategoriesAdapter;
import com.hyperloop.uploadapp.upload.listeners.OnItemClickCategoryListener;

import java.util.ArrayList;
import java.util.List;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener, UploadView,
        OnItemClickCategoryListener{

    private static final int REQUEST_IMAGE_CAPTURE = 0;
    private static final int REQUEST_IMAGE_GALLERY = 1;
    private static final String PHOTO_URL = "https://dummyimage.com/600x400/151994/0011ff";

    private Context mContext;

    private Button buttonLogOut, buttonPicture, buttonGallery, uploadButton;
    private TextView textViewWelcome;
    private RecyclerView recyclerView;
    private EditText editText;
    private View containerText;

    UploadPresenter uploadPresenter;

    //List where are stored the ategories with attributes to define if it's selected or not
    private List<Category> categoriesList;

    //Adaoter to manage the lrecyclerView
    private CategoriesAdapter adapter;

    //Listener to handle the name of the category
    private OnItemClickCategoryListener listener;

    private String categoryName;
    private String name;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        user = FirebaseAuth.getInstance().getCurrentUser();


        mContext = this;


        uploadPresenter = new UploadPresenterImpl(this);
        uploadPresenter.onCreate();

        textViewWelcome = (TextView) findViewById(R.id.welcomeTextView);

        containerText = findViewById(R.id.container);
        editText        = (EditText) containerText.findViewById(R.id.editText);

        recyclerView = (RecyclerView) findViewById(R.id.categoriesRecyclerView);

        buttonLogOut  = (Button) findViewById(R.id.logoutButton);
        buttonGallery = (Button) findViewById(R.id.galleryButton);
        buttonPicture = (Button) findViewById(R.id.takePictureButton);
        uploadButton  = (Button) findViewById(R.id.updateButton);


        buttonLogOut.setOnClickListener(this);
        buttonGallery.setOnClickListener(this);
        buttonPicture.setOnClickListener(this);
        uploadButton.setOnClickListener(this);

        listener = this;

        setAdapter();

        initializeVariables();

        textViewWelcome.setText(String.format("Welcome %s", name));

    }

    private void initializeVariables() {
        categoryName = categoriesList.get(0).getCategory();
        name = "";
        if(user.getEmail() != null) {
            name = user.getEmail();
        }

    }

    private void setAdapter() {

        categoriesList = new ArrayList<>();

        categoriesList.add(new Category("Category 1", true));
        categoriesList.add(new Category("Category 2", false));

        adapter = new CategoriesAdapter(mContext, categoriesList, listener);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.logoutButton:
                uploadPresenter.signOff();
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;

            case R.id.takePictureButton:
                dispatchTakePictureIntent();

                break;

            case R.id.galleryButton:
                dispatchGalleryIntent();
                break;

            case R.id.updateButton:

                //Hyde keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                //Get the timestamp
                Long tsLong = System.currentTimeMillis()/1000;
                String ts = tsLong.toString();


                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("posts");

                //DatabaseReference categoriesRef = ref.child("categories");

                ref.push().setValue(new Post(user.getEmail(), getText(), PHOTO_URL, categoryName, ts), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            Toast.makeText(mContext, "Data could not be saved " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(mContext, "Post uploated", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                break;
        }
    }

    //POJO to send the post  to Firebase database
    public static class Post {

        public String user;
        public String text;
        public String photo_url;
        public String category;
        public String upload_time;

        public Post(String user, String text, String photo_url, String category, String upload_time) {
            this.user = user;
            this.text = text;
            this.photo_url = photo_url;
            this.category = category;
            this.upload_time = upload_time;
        }
    }

    private void dispatchGalleryIntent() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, REQUEST_IMAGE_GALLERY);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            switch (requestCode){
                case REQUEST_IMAGE_CAPTURE:
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    break;
                case REQUEST_IMAGE_GALLERY:

                    break;
            }


        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClickCategory(Category category) {
        //Set the category name
        categoryName = category.getCategory();
    }

    private String getText(){
        String s = "";
        if(editText.getText() != null){
            s = editText.getText().toString();
        }
        return s;
    }
}
