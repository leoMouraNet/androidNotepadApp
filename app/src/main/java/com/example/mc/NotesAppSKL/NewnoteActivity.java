package com.example.mc.NotesAppSKL;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class NewnoteActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;
    private static final int VOICE_REQUEST = 1234;
    public Note note = new Note();
    MySQLiteHelper db = new MySQLiteHelper(this);

    EditText txtNoteTittle,txtNoteLocation,txtNoteMessage;
    Button btnSelectPicture,btnTakePicture,btnVoiceNote,btnSaveNote;
    ImageView imageView;

    private String localNote,localLocation,localMessage, localImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newnote);
        Intent mIntent = getIntent();
        note.id = mIntent.getIntExtra("intID", 0);

        if (note.id != 0) {
            note = db.getNote(note.id);
        }
        getVisualElements();


    }//end onCreate

    public void getVisualElements(){

        //get my elements

        txtNoteTittle = (EditText) findViewById(R.id.txtNoteTittle);
        txtNoteLocation = (EditText) findViewById(R.id.txtNoteLocation);
        txtNoteMessage = (EditText) findViewById(R.id.txtNoteMessage);

        imageView = (ImageView) findViewById(R.id.imageView);

        if (note.id != 0) {
            txtNoteLocation.setText(note.location);
            txtNoteTittle.setText(note.title);
            txtNoteMessage.setText(note.message);
        }

        btnTakePicture = (Button) findViewById(R.id.btnTakePicture);
        btnSelectPicture = (Button) findViewById(R.id.btnSelectPicture);
        btnVoiceNote = (Button) findViewById(R.id.btnVoiceNote);
        btnSaveNote = (Button) findViewById(R.id.btnSaveNote);



        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("======= CLICK ON +TAKE+ PICTURE BUTTON =====");

            }
        });


        btnSelectPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("======= CLICK ON +SELECT+ PICTURE BUTTON =====");


                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);



            }
        });

        btnVoiceNote.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                System.out.println("======= CLICK ON +VOICE NOTE+  BUTTON =====");

                Intent voiceIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                voiceIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                voiceIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please Speak");
                startActivityForResult(voiceIntent, VOICE_REQUEST);


            }
        });

        btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("======= CLICK ON +SAVE NOTE+  BUTTON =====");

                note.title = txtNoteTittle.getText().toString();
                note.location = txtNoteLocation.getText().toString();
                note.message = txtNoteMessage.getText().toString();

                if (note.id!=0) {
                    db.updateNote(note);
                } else {
                    db.addNote(note);
                }

                localNote = txtNoteTittle.getText().toString();
                localLocation = txtNoteLocation.getText().toString();
                localMessage = txtNoteMessage.getText().toString();

                localImage = String.valueOf(imageView.getTag());

                System.out.println("--------------------");
                System.out.println("Note: " + localNote);
                System.out.println("Location: " + localLocation);
                System.out.println("Message: " + localMessage);
                System.out.println("Image: " + localImage);
                finish();

            }
        });


    }//end getVisualElements


    public void onActivityResult(int requestCode, int resultCode, Intent data) {




        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);

                try{
                    System.out.println("==== Save Picture Here! ===");
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                    imageView.setImageBitmap(bitmap); //Place my Image on ImageView

                }catch (IOException e){
                    e.printStackTrace();
                }


            }

            if (requestCode == VOICE_REQUEST) {
                // Save the voice here
                System.out.println("==== Save Voice Here! ===");
            }

        }




    }

    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here
        return uri.getPath();
    }




}//end NewnoteActivity
