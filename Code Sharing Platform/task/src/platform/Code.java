package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@Entity
@JsonPropertyOrder(alphabetic = true)
public class Code {

    @Id
    @JsonIgnore
    private String uuid = UUID.randomUUID().toString();
    private String code;
    private String date = LocalDateTime.now()
            .format(DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm:ss"));
    @JsonIgnore
    private long secondsUpdated = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    @JsonProperty("time")
    private long timeLeft;
    @JsonIgnore
    private boolean timeRestricted;
    @JsonProperty("views")
    private int viewsLeft;
    @JsonIgnore
    private boolean viewsRestricted;
    @JsonIgnore
    private boolean available = true;

    public Code() {
    }

    public void setTimeRestricted() {
        this.timeRestricted = timeLeft > 0;
    }

    public void setViewsRestricted() {
        this.viewsRestricted = viewsLeft > 0;
    }

    public Code(String code) {
        this.code = code;
    }

    public void updateViews() {
        if (viewsRestricted) {
            viewsLeft--;
            if (viewsLeft == 0) {
                available = false;
            }
        }
    }

    public void updateTime() {
        if (timeRestricted) {
            timeLeft = timeLeft - (LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - secondsUpdated);
            secondsUpdated = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
            if (timeLeft <= 0) {
                timeLeft = 0;
                available = false;
            }
        }
    }
}
