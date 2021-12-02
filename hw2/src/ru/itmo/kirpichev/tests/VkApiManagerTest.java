package ru.itmo.kirpichev.tests;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.itmo.kirpichev.vk.VkApiHashtagClient;
import ru.itmo.kirpichev.vk.VkApiManager;
import ru.itmo.kirpichev.vk.dto.HashtagDto;
import ru.itmo.kirpichev.vk.dto.ItemDto;
import ru.itmo.kirpichev.vk.dto.ResponseDto;

import static org.mockito.Matchers.any;

/**
 * @author ilyakirpichev
 */
class VkApiManagerTest {

    @Mock
    private VkApiHashtagClient client;

    private VkApiManager vkApiManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        vkApiManager = new VkApiManager(client);
    }

    @Test
    public void getCountPostsByHourFromStartTime_whenOneHour_thenReturnGivenArrayTest() {
        getCountPostsByHourWithExpectedResult(List.of(10L));
    }

    @Test
    public void getCountPostsByHourFromStartTime_whenTwoHours_thenReturnGivenArrayTest() {
        getCountPostsByHourWithExpectedResult(List.of(10L, 5L));
    }

    @Test
    public void getCountPostsByHourFromStartTime_whenExistsZero_thenReturnGivenArrayTest() {
        getCountPostsByHourWithExpectedResult(List.of(10L, 5L, 0L, 14L));
    }

    @Test
    public void getCountPostsByHourFromStartTime_when24Hours_thenReturnGivenArrayTest() {
        List<Long> reqs = new ArrayList<>();
        for (int i = 1; i <= 24; ++i) {
            reqs.add((long) (i * 100 + i));
        }
        getCountPostsByHourWithExpectedResult(reqs);
    }

    private void getCountPostsByHourWithExpectedResult(List<Long> requestsPerHour) {
        LocalDateTime now = LocalDateTime.now();

        List<ItemDto> itemsDto = getItems(requestsPerHour, now);
        HashtagDto dto = new HashtagDto(new ResponseDto(itemsDto));

        Mockito.when(client.getResponseDto(any())).thenReturn(dto);

        List<Long> actual = vkApiManager.getCountPostsByHourFromStartTime(dto, requestsPerHour.size(), now);
        Assertions.assertEquals(requestsPerHour, actual);

    }

    private List<ItemDto> getItems(List<Long> requestsPerHour, LocalDateTime startTime) {
        List<ItemDto> items = new ArrayList<>();
        for (int cnt = 1; cnt <= requestsPerHour.size(); ++cnt) {
            Long reqs = requestsPerHour.get(cnt - 1);
            LocalDateTime queriesTime = startTime.minusHours(cnt).plusMinutes(5);
            for (int i = 0; i < reqs; ++i) {
                items.add(new ItemDto("", Timestamp.valueOf(queriesTime).getTime() / 1000L));
            }
        }
        return items;
    }
}