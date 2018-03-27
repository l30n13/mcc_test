package com.mcc.l30n.mcc;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.mcc.l30n.mcc.databinding.ActivityMainBinding;
import com.mcc.l30n.mcc.helper.ImagePickerHelper;
import com.mcc.l30n.mcc.model.Profile;
import com.mcc.l30n.mcc.utils.DatabaseHandler;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private String profileImage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePickerHelper imagePickerHelper = new ImagePickerHelper(MainActivity.this, MainActivity.this);
                imagePickerHelper.pickerImage();
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty()) {
                    if (Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.getText().toString()).matches()) {
                        Profile profile = new Profile(
                                binding.etName.getText().toString(),
                                binding.etEmail.getText().toString(),
                                binding.etPhoneNumber.getText().toString(),
                                profileImage,
                                Integer.valueOf(binding.etAge.getText().toString()),
                                0
                        );
                        DatabaseHandler databaseHandler = new DatabaseHandler(MainActivity.this);
                        databaseHandler.addProfile(profile);

                        Toast.makeText(MainActivity.this, "Profile added successfully", Toast.LENGTH_SHORT).show();

                        binding.etName.setText("");
                        binding.etAge.setText("");
                        binding.etEmail.setText("");
                        binding.etPhoneNumber.setText("");
                        binding.ivProfileImage.setImageResource(R.drawable.people);
                        profileImage = "";
                    } else {
                        View view = binding.etEmail;
                        binding.etName.setError("Please provide valid email");
                        view.requestFocus();
                    }
                }
            }
        });

    }

    private boolean isEmpty() {
        int count = 0;
        View view;

        if (binding.etName.getText().toString().isEmpty()) {
            view = binding.etName;
            binding.etName.setError(getString(R.string.required_field));
            view.requestFocus();
            count++;
        }
        if (binding.etAge.getText().toString().isEmpty()) {
            view = binding.etAge;
            binding.etAge.setError(getString(R.string.required_field));
            view.requestFocus();
            count++;
        }
        if (binding.etEmail.getText().toString().isEmpty()) {
            view = binding.etEmail;
            binding.etEmail.setError(getString(R.string.required_field));
            view.requestFocus();
            count++;
        }
        if (binding.etPhoneNumber.getText().toString().isEmpty()) {
            view = binding.etPhoneNumber;
            binding.etPhoneNumber.setError(getString(R.string.required_field));
            view.requestFocus();
            count++;
        }
        if (profileImage.equals("")) {
            Toast.makeText(MainActivity.this, "Image cannot not be empty.", Toast.LENGTH_SHORT).show();
            count++;
        }

        return count != 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_show_all:
                startActivity(new Intent(MainActivity.this, ShowAllActivity.class));
                break;
            case R.id.action_show_gallery:
                startActivity(new Intent(MainActivity.this, GalleryActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            Image image = ImagePicker.getFirstImageOrNull(data);
            Bitmap bmp;
            ByteArrayOutputStream bos;
            try {
                bmp = BitmapFactory.decodeFile(image.getPath());
                binding.ivProfileImage.setImageBitmap(bmp);
                bos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 50, bos);
                profileImage = Base64.encodeToString(bos.toByteArray(), Base64.DEFAULT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
