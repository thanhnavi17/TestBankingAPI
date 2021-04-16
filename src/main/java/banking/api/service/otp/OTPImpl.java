package banking.api.service.otp;

import banking.api.model.OTP;
import banking.api.repository.OTPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OTPImpl implements OTPService{

    @Autowired
    private OTPRepository otpRepository;

    @Override
    public boolean otpCheck(String cif, String otp) {
        OTP objOTP = otpRepository.findByCif(cif);
        if(objOTP != null && objOTP.getOtp().equals(otp)){
            return true;
        }
        return false;
    }

    @Override
    public List<OTP> getAll() {
        return null;
    }

    @Override
    public OTP getById(Integer id) {
        return null;
    }

    @Override
    public boolean create(OTP obj) {
        return false;
    }

    @Override
    public boolean update(OTP obj) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
