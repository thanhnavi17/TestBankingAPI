package banking.api.service.otp;

import banking.api.model.OTP;
import banking.api.service.Action;
import org.springframework.stereotype.Service;

@Service
public interface OTPService extends Action<OTP, Integer> {
    boolean otpCheck(String cif, String otp);
}
