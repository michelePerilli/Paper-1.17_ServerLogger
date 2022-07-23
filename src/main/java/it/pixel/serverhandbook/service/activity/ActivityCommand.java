package it.pixel.serverhandbook.service.activity;

import it.pixel.serverhandbook.model.PlayerActivity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The type Logging service.
 */
public class ActivityCommand extends ActivityUtils {

    /**
     * Show server log list
     *
     * @param player player
     * @throws Exception the exception
     */
    public static void show(Player player) throws Exception {
        sendMessage(player, getAllActivities().stream().map(ActivityUtils::prepareActivityString).toList());
    }


    /**
     * Show server log list
     *
     * @param player player
     * @param args   the args
     * @throws Exception the exception
     */
    public static void find(Player player, String[] args) throws Exception {
        if (args.length <= 1)
            return;
        String searchKey = String.join(" ", getParameters(args));
        sendMessage(player, getAllActivitiesByPlayer(searchKey).stream().map(ActivityUtils::prepareActivityString).toList());
    }


    /**
     * Show server log list
     *
     * @throws Exception the exception
     */
    public static void stats(Player player) throws Exception {

        List<Points> userTime = new ArrayList<>();

        Map<String, List<PlayerActivity>> mappa = getAllActivities().stream().collect(Collectors.groupingBy(PlayerActivity::playerName));

        for (Map.Entry<String, List<PlayerActivity>> playerName : mappa.entrySet()) {
            long time = 0L;
            List<PlayerActivity> values = playerName.getValue();
            if (values.get(0).activity().equals(LEFT)) {
                values.remove(0);
            }
            int size = values.size();
            for (int i = 0; i < size - 1; i++) {
                if (values.get(i).activity().equals(values.get(i + 1).activity())) {
                    values.set(i, new PlayerActivity(true, null, null, null));
                }
            }
            values = new ArrayList<>(values.stream().filter(x -> !x.deleted()).toList());

            List<Long> occurrences = new ArrayList<>(values.stream().map(x -> simpleStringToDate(x.date())).toList());
            Long lastLogIn = null;
            if (occurrences.size() % 2 == 1) {
                lastLogIn = occurrences.remove(occurrences.size() - 1);
            }

            for (int i = 0; i < occurrences.size() - 1; i = i + 2) {
                time += occurrences.get(i + 1) - occurrences.get(i);
            }

            if (lastLogIn != null) {
                time += simpleStringToDate(getCurrentDate()) - lastLogIn;
            }
            userTime.add(new Points(playerName.getKey(), time));

        }
        userTime = userTime.stream().sorted().toList();

        List<String> toPrint = new ArrayList<>(userTime.stream().sorted().map(x -> prepareActivityReportString(x.player(), x.time)).toList());
        toPrint.set(0, prepareActivityReportStringGold(userTime.get(0).player(), userTime.get(0).time()));
        sendMessage(player, toPrint);
    }

    private record Points(String player, Long time) implements Comparable<Points> {
        @Override
        public int compareTo(@NotNull ActivityCommand.Points o) {
            return this.time.compareTo(o.time()) * -1;
        }
    }

}
