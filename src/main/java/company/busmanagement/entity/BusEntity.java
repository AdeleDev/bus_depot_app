package company.busmanagement.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "bus_information")
public class BusEntity extends BaseEntity {

    @Column(unique = true)
    @NotNull
    @Size(min = 4, max = 10)
    private String number;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ColorEnum color;

    @NotNull
    @Size(min = 4, max = 72)
    private Long peopleAmount;

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @PastOrPresent
    @Size(min = 10, max = 10)
    //@Temporal(TemporalType.DATE)
    private LocalDate maintenanceDate;

    @NotNull
    @Size(min = 1, max = 200)
    private String trip;

    @ElementCollection(targetClass = Long.class, fetch = FetchType.EAGER)
    @NotNull
    private List<Long> driversInfo = new ArrayList<>();

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ColorEnum getColor() {
        return color;
    }

    public void setColor(ColorEnum color) {
        this.color = color;
    }

    public Long getPeopleAmount() {
        return peopleAmount;
    }

    public void setPeopleAmount(Long peopleAmount) {
        this.peopleAmount = peopleAmount;
    }


    public LocalDate getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(LocalDate maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }


    public List<Long> getDriversInfo() {
        return driversInfo;
    }

    public void setDriversInfo(List<Long> driversInfo) {
        this.driversInfo = driversInfo;
    }

    public enum ColorEnum {
        YELLOW("yellow"), GREEN("green"), BLUE("blue"), GREY("grey"), BLACK("black"), OTHER("other");

        private final String value;

        ColorEnum(String value) {
            this.value = value;
        }

        public static ColorEnum fromValue(String value) {
            for (ColorEnum color : ColorEnum.values()) {
                if (color.value.equals(value)) {
                    return color;
                }
            }
            throw new IllegalArgumentException("Unexpected color value '" + value + "'");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusEntity bus = (BusEntity) o;
        return Objects.equals(number, bus.number)
                && color == bus.color
                && Objects.equals(peopleAmount, bus.peopleAmount)
                && Objects.equals(maintenanceDate, bus.maintenanceDate)
                && Objects.equals(trip, bus.trip)
                && Objects.equals(driversInfo, bus.driversInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, color, peopleAmount, maintenanceDate, trip, driversInfo);
    }
}
