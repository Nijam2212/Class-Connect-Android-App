package com.example.notice;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends BaseActivity {

    TextView nameText, roleText, idText, emailText, divisionOrSubjectText, extraInfo1Text, extraInfo2Text;
    ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_profile); // Make sure this matches your layout file name

        nameText = findViewById(R.id.nameText);
        roleText = findViewById(R.id.roleText);
        idText = findViewById(R.id.idText);
        emailText = findViewById(R.id.emailText);
        divisionOrSubjectText = findViewById(R.id.divisionOrSubjectText);
        extraInfo1Text = findViewById(R.id.extraInfo1Text);
        extraInfo2Text = findViewById(R.id.extraInfo2Text);
        profileImage = findViewById(R.id.profileImage);

        // 🔧 Change "student" to "teacher" to test other profile type
        String role = "student"; // or "teacher"

        if (role.equals("student")) {
            nameText.setText("Amit Deshmukh");
            roleText.setText("Student");
            idText.setText("ID: S12345");
            emailText.setText("Email: amit@student.com");
            divisionOrSubjectText.setText("Division: A-Batch");
            extraInfo1Text.setText("Parent Name: Mr. Deshmukh");
            extraInfo2Text.setText("Address: Pune, Maharashtra");
        } else {
            nameText.setText("Mrs. Rane");
            roleText.setText("Teacher");
            idText.setText("ID: T56789");
            emailText.setText("Email: rane@school.com");
            divisionOrSubjectText.setText("Subject: Physics");
            extraInfo1Text.setText("Mobile: 9876543210");
            extraInfo2Text.setText("Experience: 5 Years");
        }

        // 👤 Optional static image (you can use your own drawable)
        //profileImage.setImageResource(R.drawable.ic_profile); // Replace with real profile image if needed
    }
}
