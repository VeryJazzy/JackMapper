package BusApp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bus {

    private String name;
    private List<Integer> howLongs = new ArrayList<>();

    public Bus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getHowLongs() {
        return howLongs;
    }

    public void addTime(int time) {
        howLongs.add(time);
        Collections.sort(howLongs);
    }

    @Override
    public String toString() {
      return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Bus)) {
            return false;
        }
        Bus busO = (Bus) o;
        if (this.name.equals(busO.name)) {
            return true;
        }
        return false;
    }

    public String getInfo() {
        StringBuilder info = new StringBuilder(this.name + ": ");

        for (int i = 0; i < howLongs.size(); i++) {

            if (i == howLongs.size() - 1) {
                if (howLongs.get(i).equals(0)) {
                    info.append("due");
                } else {
                    info.append(howLongs.get(i)).append(" minutes");
                }
                continue;
            }
            if (howLongs.get(i).equals(0)) {
                info.append("due, ");
            } else {
                info.append(howLongs.get(i)).append(", ");
            }
        }
        return info.toString();
    }
}
