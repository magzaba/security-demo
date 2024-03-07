package mzaba.spring.security.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CachedValue {

    private int attempts;
    private LocalDateTime blockedTimestamp;
    private LocalDateTime blockedUntilTimestamp;
    public void registerAttempt() {
        this.attempts++;
    }

}
