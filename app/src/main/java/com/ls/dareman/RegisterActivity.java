package com.ls.dareman;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    MaterialEditText username, email, password, comment;
    Button btn_register;
    Spinner spinner;
    FirebaseAuth auth;
    DatabaseReference reference;
    ProgressDialog progress;

    private String spinnerItems[] = {"", "男性", "女性"};
    private String selectedGender = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.create_account_title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_register = findViewById(R.id.btn_register);
        spinner = findViewById(R.id.selectSex);
        comment = findViewById(R.id.comment);

        progress = new ProgressDialog(this);

        // ArrayAdapter
        ArrayAdapter<String> adapter
                = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, spinnerItems);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // spinner に adapter をセット
        spinner.setAdapter(adapter);

        // リスナーを登録
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //　アイテムが選択された時
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                String item = (String)spinner.getSelectedItem();
                selectedGender = item;
//                Toast.makeText(RegisterActivity.this, "selected gender : " + selectedGender, Toast.LENGTH_SHORT).show();
//                textView.setText(item);
            }

            //　アイテムが選択されなかった
            public void onNothingSelected(AdapterView<?> parent) {
                //
                selectedGender = "";
            }
        });

        auth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(view -> {
            String txt_username = username.getText().toString();
            String txt_email = email.getText().toString();
            String txt_password = password.getText().toString();
            String txt_comment = comment.getText().toString();

            if (TextUtils.isEmpty(txt_username)
                    || TextUtils.isEmpty(txt_email)
                    || TextUtils.isEmpty(txt_password)
                    || TextUtils.isEmpty(txt_comment)
                    || TextUtils.isEmpty(selectedGender)) {
                Toast.makeText(RegisterActivity.this, "全枠入力必須です", Toast.LENGTH_SHORT).show();
            } else if (txt_password.length() < 6 ){
                Toast.makeText(RegisterActivity.this, "パスワードは６文字以上が必須です", Toast.LENGTH_SHORT).show();
            } else {
                register(txt_username, txt_email, txt_password, txt_comment, selectedGender);
                showLoading(progress);
            }
        });
    }

    private void register(final String username, String email, String password, String comment, String gender){

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        assert firebaseUser != null;
                        String userId = firebaseUser.getUid();

                        reference = FirebaseDatabase.getInstance().getReference("Users").child(userId);

                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("id", userId);
                        hashMap.put("username", username);
                        hashMap.put("imageURL", "default");
                        hashMap.put("status", "offline");
                        hashMap.put("search", username.toLowerCase());
//                        hashMap.put("gender", gender.toLowerCase());
                        hashMap.put("comment", comment);

                        if (gender.equals("男性")) {
                            hashMap.put("gender", "male");
                        } else {
                            hashMap.put("gender", "female");
                        }

                        reference.setValue(hashMap).addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                dismissLoading(progress);
                                Intent intent = new Intent(RegisterActivity.this, BaseActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        });
                    } else {
                        dismissLoading(progress);
                        Toast.makeText(RegisterActivity.this, "入力したEmailはすでに存在してます。", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showLoading(ProgressDialog progress) {
        progress.setTitle("Loading");
        progress.setMessage("登録中、しばらくお待ちください。");
        progress.setCancelable(false);
        progress.show();
    }

    private void dismissLoading(ProgressDialog progress) {
        progress.dismiss();
    }
}
