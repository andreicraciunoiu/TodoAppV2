package home.example.projects.loginapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginSuccessActivity extends AppCompatActivity {
    private SQLiteDBHelper helper;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        editText = (EditText) findViewById(R.id.write_task);
        Button addTaskButton = (Button) findViewById(R.id.button_add_task);
        helper = new SQLiteDBHelper(this);


        TextView nameText = (TextView) findViewById(R.id.welcome_user_text);
        TextView emailText = (TextView) findViewById(R.id.login_success_email);
        Button logoutButton = (Button) findViewById(R.id.logout_button);
        Intent intent = getIntent();

        String loginName = intent.getStringExtra("fullname");
        String loginEmail = intent.getStringExtra("email");
        nameText.setText(getString(R.string.activity_welcome_text) + loginName + getString(R.string.exclamation_mark));
        emailText.setText(loginEmail);

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTask = editText.getText().toString();
                if(editText.length()!=0){
                    AddData(newTask);
                    editText.setText("");

                }else {
                    Toast.makeText(LoginSuccessActivity.this, "Write a task to be added", Toast.LENGTH_SHORT).show();
                }
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(LoginSuccessActivity.this);
                builder.setTitle(getString(R.string.logout_info));
                builder.setMessage(getString(R.string.double_check_question));
                builder.setPositiveButton(getString(R.string.confirm_logout), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(LoginSuccessActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                builder.setNegativeButton(getString(R.string.cancel_logout), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    private void AddData(String newTask) {

        boolean insertData = helper.addTask(newTask);

        if(insertData){
            Toast.makeText(this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
        }
    }
}
