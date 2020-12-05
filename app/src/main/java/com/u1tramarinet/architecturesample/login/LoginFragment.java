package com.u1tramarinet.architecturesample.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.support.v4.app.INotificationSideChannel;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.u1tramarinet.architecturesample.R;

import java.util.Locale;

public class LoginFragment extends Fragment {
    private static final int PASSWORD_LENGTH_MIN = 8;
    private Button loginButton;
    private boolean isOkMailAddress = false;
    private boolean isOkPassword = false;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
    }

    private void initializeViews(@NonNull View root) {
        ImageView mailAddressFeedbackIcon = root.findViewById(R.id.mail_address_feedback_icon);
        TextView mailAddressFeedback = root.findViewById(R.id.mail_address_feedback);
        EditText mailAddressInput = root.findViewById(R.id.mail_address_input);
        mailAddressInput.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(@NonNull String text) {
                super.onTextChanged(text);
                int length = text.length();
                int visibility = View.VISIBLE;
                int iconRes = R.drawable.ic_baseline_check_circle_24;
                int colorRes = R.color.ok;
                String message = "";
                boolean result = false;
                if (length <= 0) {
                    visibility = View.INVISIBLE;
                } else if (!isValidEmail(text)) {
                    iconRes = R.drawable.ic_baseline_error_24;
                    message = getString(R.string.mail_address_error_incorrect_format);
                    colorRes = R.color.ng;
                } else {
                    result = true;
                }
                int color = ContextCompat.getColor(getContext(), colorRes);
                mailAddressFeedbackIcon.setImageResource(iconRes);
                mailAddressFeedbackIcon.setColorFilter(color);
                mailAddressFeedback.setText(message);
                mailAddressFeedback.setTextColor(color);
                mailAddressFeedbackIcon.setVisibility(visibility);
                mailAddressFeedback.setVisibility(visibility);
                isOkMailAddress = result;
                updateLoginButtonState();
            }
        });

        ImageView passwordFeedbackIcon = root.findViewById(R.id.password_feedback_icon);
        TextView passwordFeedback = root.findViewById(R.id.password_feedback);
        EditText passwordInput = root.findViewById(R.id.password_input);
        passwordInput.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(@NonNull String text) {
                super.onTextChanged(text);
                int length = text.length();
                int visibility = View.VISIBLE;
                int iconRes = R.drawable.ic_baseline_check_circle_24;
                int colorRes = R.color.ok;
                String message = "";
                boolean result = false;
                if (length <= 0) {
                    visibility = View.INVISIBLE;
                } else if (length < PASSWORD_LENGTH_MIN) {
                    iconRes = R.drawable.ic_baseline_error_24;
                    message = getString(R.string.password_error_shorter, PASSWORD_LENGTH_MIN - length);
                    colorRes = R.color.ng;
                } else {
                    result = true;
                }
                int color = ContextCompat.getColor(getContext(), colorRes);
                passwordFeedbackIcon.setImageResource(iconRes);
                passwordFeedbackIcon.setColorFilter(color);
                passwordFeedback.setText(message);
                passwordFeedback.setTextColor(color);
                passwordFeedbackIcon.setVisibility(visibility);
                passwordFeedback.setVisibility(visibility);
                isOkPassword = result;
                updateLoginButtonState();
            }
        });
        loginButton = root.findViewById(R.id.login_button);
        loginButton.setOnClickListener(v -> login());
        updateLoginButtonState();
    }

    private boolean isValidEmail(@NonNull String text) {
        return Patterns.EMAIL_ADDRESS.matcher(text).matches();
    }

    private void updateLoginButtonState() {
        if (loginButton == null) return;
        boolean loginAvailable = isOkMailAddress && isOkPassword;
        loginButton.setEnabled(loginAvailable);
    }

    private void login() {
        Toast.makeText(getContext(), R.string.login_message, Toast.LENGTH_SHORT).show();
    }

    private static class MyTextWatcher implements TextWatcher {
        private final static String TAG = MyTextWatcher.class.getSimpleName();
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Log.d(TAG, String.format(Locale.JAPAN, "beforeTextChanged() s=%s, start=%d, count=%d, after=%d", s, start, count, after));
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.d(TAG, String.format(Locale.JAPAN, "onTextChanged() s=%s, start=%d, before=%d, count=%d", s, start, before, count));
            onTextChanged((s != null) ? s.toString() : "");
        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.d(TAG, String.format(Locale.JAPAN, "afterTextChanged() s=%s", s));
        }

        public void onTextChanged(@NonNull String text) {
            Log.d(TAG, String.format(Locale.JAPAN, "onTextConfirmed() text=%s", text));
        }
    }
}