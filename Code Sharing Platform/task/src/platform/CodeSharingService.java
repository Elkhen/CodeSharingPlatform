package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class CodeSharingService {
    private final CodeRepository codeRepository;

    @Autowired
    public CodeSharingService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public Code saveCode(Code code) {
        code.setTimeRestricted();
        code.setViewsRestricted();
        return codeRepository.save(code);
    }

    public Map<String, String> getSavedCodeUUID(Code code) {
        return Map.of("id", code.getUuid());
    }

    private Code getCodeFromDatabase(String UUID) {
        return codeRepository.findByUuid(UUID)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
    }

    public Code getCode(String UUID) {
        Code code = getCodeFromDatabase(UUID);
        updateCodeAvailability(code);
        return code;
    }

    private void updateCodeAvailability(Code code) {
        code.updateTime();
        if (!code.isAvailable()) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        code.updateViews();
        codeRepository.save(code);
        if (!code.isAvailable()) {
            codeRepository.delete(code);
        }
    }

    public List<Code> getTenMostRecentUnrestrictedCodes() {
        List<Code> allCodes = new ArrayList<>();
        codeRepository.findAll().forEach(
                code -> {
                    if (code.getViewsLeft() == 0 && code.getTimeLeft() == 0) {
                        allCodes.add(code);
                    }
                }
        );
        Collections.reverse(allCodes);
        return allCodes.size() > 10 ? allCodes.subList(0, 10) : allCodes;
    }
}
