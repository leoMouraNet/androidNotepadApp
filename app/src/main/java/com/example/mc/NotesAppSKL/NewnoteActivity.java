package com.example.mc.NotesAppSKL;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class NewnoteActivity extends AppCompatActivity {

    EditText txtNoteTittle,txtNoteLocation,txtNoteMessage;
    Button btnSelectPicture,btnTakePicture,btnVoiceNote,btnSaveNote;
    ImageView imageView;

    private String localNote,localLocation,localMessage, localImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newnote);


        getVisualElements();


    }//end onCreate

    public void getVisualElements(){

        //get my elements

        txtNoteTittle = (EditText) findViewById(R.id.txtNoteTittle);
        txtNoteLocation = (EditText) findViewById(R.id.txtNoteLocation);
        txtNoteMessage = (EditText) findViewById(R.id.txtNoteMessage);

        imageView = (ImageView) findViewById(R.id.imageView);

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

            }
        });

        btnVoiceNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("======= CLICK ON +VOICE NOTE+  BUTTON =====");

            }
        });

        btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("======= CLICK ON +SAVE NOTE+  BUTTON =====");

                localNote = txtNoteTittle.getText().toString();
                localLocation = txtNoteLocation.getText().toString();
                localMessage = txtNoteMessage.getText().toString();

                localImage = String.valueOf(imageView.getTag());


                System.out.println("--------------------");
                System.out.println("Note: " + localNote);
                System.out.println("Location: " + localLocation);
                System.out.println("Message: " + localMessage);
                System.out.println("Image: " + localImage);


            }
        });


    }//end getVisualElements


}//end NewnoteActivity
