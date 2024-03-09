package org.example.searadar.mr2313;

import java.util.List;

import org.example.searadar.mr2313.convert.Mr2313Converter;
import org.example.searadar.mr2313.station.Mr2313StationType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ru.oogis.searadar.api.message.InvalidMessage;
import ru.oogis.searadar.api.message.RadarSystemDataMessage;
import ru.oogis.searadar.api.message.SearadarStationMessage;
import ru.oogis.searadar.api.message.TrackedTargetMessage;

/**
 * Тесты для Mr2313
 * Проверка правильности работы конвертера
*/
public class Mr2313Tests {
        /**
      * Данный кластер тестов предназначен для проверки 
      правильности конвертации сообщений для НРЛС MР-231-3
      
      Тестируемые форматы сообщений:
      --TTM(TrackedTargetMessage)
      --RSD(RadarSystemData Messages)

      Правильность конвертации сообщений в себя включает:
      1) Проверка корректного конвертирования сообщения
      2) Проверка типа обработанного сообщения
      3) Сравнение конвертированных сообщений с ожидаемыми
     */
        @Test
        @DisplayName("Проверка конвертации сообщения типа TTM")
        public void testTTMforMr2313Converter() {
            //Создание конвертера для станции MP-231-3
            Mr2313StationType mr2313 = new Mr2313StationType();
            Mr2313Converter converter = mr2313.createConverter();

            // Тестирование сообщений TTM
            List<SearadarStationMessage> searadarMessages3 = converter.convert("$RATTM,66,28.71,341.1,T,57.6,024.5,T,0.4,4.1,N,b,L,,003313,А*42");
            
            // Проверка типа сообщения
            SearadarStationMessage message = searadarMessages3.get(0);
            assertTrue(message instanceof TrackedTargetMessage);

            // Преобразование сообщения в TrackedTargetMessage
            TrackedTargetMessage targetMessage = (TrackedTargetMessage) message;
            
            //TrackedTargetMessage(targetNumber=66, distance=28.71, bearing=341.1, course=24.5, speed=57.6, type=UNKNOWN, status=LOST, iff=FRIEND)
            //Осуществление проверок 
            assertEquals(66, targetMessage.getTargetNumber());
            assertEquals(28.71, targetMessage.getDistance());
            assertEquals(341.1, targetMessage.getBearing());
            assertEquals(24.5, targetMessage.getCourse());
            assertEquals(57.6, targetMessage.getSpeed());
            assertEquals("UNKNOWN", targetMessage.getType().name());
            assertEquals("LOST", targetMessage.getStatus().name());
            assertEquals("FRIEND", targetMessage.getIff().name());


        }

        @Test
        @DisplayName("Проверка конвертации сообщения типа RSD")
        public void testRSDforMr2313Converter(){
            //Создание конвертера для станции MP-231-3
            Mr2313StationType mr2313 = new Mr2313StationType();
            Mr2313Converter converter = mr2313.createConverter();

            List<SearadarStationMessage> searadarMessages=converter.convert("$RARSD,36.5,331.4,8.4,320.6,,,,,11.6,185.3,96.0,N,N,S*33");

            SearadarStationMessage message = searadarMessages.get(0);
            assertTrue(message instanceof TrackedTargetMessage);

            message = searadarMessages.get(0);
            assertTrue(message instanceof RadarSystemDataMessage);
            RadarSystemDataMessage radarSystemDataMessage = (RadarSystemDataMessage) message;

            //Ожидаемые данные после конвертации: RadarSystemData{msgRecTime=, initialDistance=36.5, initialBearing=331.4, movingCircleOfDistance=8.4, bearing=320.6, distanceFromShip=11.6, bearing2=185.3, distanceScale=96.0, distanceUnit=N, displayOrientation=N, workingMode=S}
            assertEquals(36.5, radarSystemDataMessage.getInitialDistance());
            assertEquals(331.4, radarSystemDataMessage.getInitialBearing());
            assertEquals(8.4, radarSystemDataMessage.getMovingCircleOfDistance());
            assertEquals(320.6, radarSystemDataMessage.getBearing());
            assertEquals(11.6, radarSystemDataMessage.getDistanceFromShip());
            assertEquals(185.3, radarSystemDataMessage.getBearing2());
            assertEquals(96.0, radarSystemDataMessage.getDistanceScale());
            assertEquals("N", radarSystemDataMessage.getDistanceUnit());
            assertEquals("N", radarSystemDataMessage.getDisplayOrientation());
            assertEquals("S", radarSystemDataMessage.getWorkingMode());


        }

}
