package ru.itmo.kirpichev.vk;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import ru.itmo.kirpichev.vk.dto.HashtagDto;


/**
 * @author ilyakirpichev
 */
public class VkApiManager {
    private final VkApiHashtagClient vkApiHashtagClient;

    public VkApiManager(VkApiHashtagClient vkApiHashtagClient) {
        this.vkApiHashtagClient = vkApiHashtagClient;
    }

    public List<Long> getPostAmountPerEachHour(String tag, int hours) {
        VkApiParams params =
                new VkApiParams("523e8fed16c237b878bd08a001c36b9a276990365f85051cb05885fdcf8cbf03d5612a5ac0b9e9c7965bd",
                        tag,
                        "5.131",
                        DateUtils.minusHoursFromNow(hours));
        LocalDateTime requestTime = LocalDateTime.now();

        HashtagDto vkDto = vkApiHashtagClient.getResponseDto(params);
        return getCountPostsByHourFromStartTime(vkDto, hours, requestTime);
    }

    public List<Long> getCountPostsByHourFromStartTime(HashtagDto vkDto, int hours, LocalDateTime startTime) {
        List<Long> result = new ArrayList<>();
        for (int i = 0; i < hours; ++i) {
            result.add(0L);
        }
        vkDto.response.items.forEach(item -> {
            LocalDateTime time = DateUtils.detLocalDateTimeFromTimestamp(item.date);
            int hourDelta = (int) ChronoUnit.HOURS.between(time, startTime);
            result.set(hourDelta, result.get(hourDelta) + 1);
        });

        return result;
    }
}
