package bytemail.domain.user.service;

import bytemail.domain.user.entity.TempUser;
import bytemail.domain.user.repository.TempUserRepository;
import bytemail.global.exception.ErrorCode;
import bytemail.global.exception.unauthorized.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TempUserService {

    private final TempUserRepository tempUserRepository;

    public void createTempUser(String email, String verifyCode) {
        tempUserRepository.findByEmail(email).ifPresent(tempUserRepository::delete);
        TempUser tempUser = new TempUser(email, verifyCode);
        tempUserRepository.save(tempUser);
    }

    public void verify(String email, String verifyCode) {
        TempUser tempUser = tempUserRepository.findByEmail(email)
                .orElseThrow(() -> new UnAuthorizedException(ErrorCode.EMAIL_NOT_AUTHORIZED));

        tempUser.verify(verifyCode);
    }
}
