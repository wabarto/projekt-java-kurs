import lombok.Getter;

import java.util.Optional;

@Getter
public class Address {
    private String city;


    public Optional<String> getCity() {
        return Optional.ofNullable(city);
    }
}
