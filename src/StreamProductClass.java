import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

//@Setter
@ToString
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StreamProductClass {
        String sku;
        String name;
        @JsonIgnore
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate date;

//        StreamProductClass() {
//
//        }

        @JsonCreator
        StreamProductClass(@JsonProperty("sku") String sku, @JsonProperty("name") String name, @JsonProperty("date") LocalDate date) {
                this.sku = sku;
                this.name = name;
                this.date = date;
        }
}
