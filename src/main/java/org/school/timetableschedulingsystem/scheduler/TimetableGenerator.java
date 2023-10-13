package org.school.timetableschedulingsystem.scheduler;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.school.timetableschedulingsystem.models.database.Lesson;
import org.school.timetableschedulingsystem.models.database.Stream;
import org.school.timetableschedulingsystem.models.database.Timeslot;
import org.school.timetableschedulingsystem.stream.StreamRepository;
import org.school.timetableschedulingsystem.timeslot.TimeslotRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.System.Logger.Level.INFO;
import static java.lang.System.getLogger;
import static java.lang.System.out;

@RequiredArgsConstructor
@Component
public class TimetableGenerator {

    private final TimeslotRepository timeslotRepository;
    private final StreamRepository streamRepository;


    public ByteArrayOutputStream generatePdf() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);

            document.open();

            //depends on unique timeslots

            // Create a new PdfPTable
            PdfPTable table = new PdfPTable(5);

            // Add the header row
            table.addCell("Day");
            table.addCell("1st Period");
            table.addCell("2nd Period");
            table.addCell("3rd Period");
            table.addCell("4th Period");

            // Add the data rows
            table.addCell("Monday");
            table.addCell("Math");
            table.addCell("Science");
            table.addCell("English");
            table.addCell("Social Studies");

            table.addCell("Tuesday");
            table.addCell("Reading");
            table.addCell("Writing");
            table.addCell("Art\nmore jazz");
            table.addCell("Music\n");

            table.addCell("Wednesday\n");
            table.addCell("Math\n");
            table.addCell("Science\n");
            table.addCell("English\n");
            table.addCell("Social Studies\n");

            table.addCell("Thursday\n");
            table.addCell("Reading\n");
            table.addCell("Writing\n");
            table.addCell("Art\n");
            table.addCell("Music\n");

            table.addCell("Friday");
            table.addCell("Math\n");
            table.addCell("Science\n");
            table.addCell("English\n");
            table.addCell("Social Studies\n");

            // Add the table to the Document
            document.add(table);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.tableToArray();

        return outputStream;
    }


    public ByteArrayOutputStream generatePdf(Stream stream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        List<Timeslot> timeslots = timeslotRepository.findAll(Sort.by(Sort.Direction.ASC, new String[]{"id_day", "id_startTime", "id_endTime"}));

        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();

        //depends on unique timeslots

        // Create a new PdfPTable
        PdfPTable table = new PdfPTable(5);

        // Add the header row
        table.addCell("Day");
        table.addCell("1st Period");
        table.addCell("2nd Period");
        table.addCell("3rd Period");
        table.addCell("4th Period");

        // Add the data rows
        table.addCell("Monday");
        table.addCell("Math");
        table.addCell("Science");
        table.addCell("English");
        table.addCell("Social Studies");

        table.addCell("Tuesday");
        table.addCell("Reading");
        table.addCell("Writing");
        table.addCell("Art\nmore jazz");
        table.addCell("Music\n");

        table.addCell("Wednesday\n");
        table.addCell("Math\n");
        table.addCell("Science\n");
        table.addCell("English\n");
        table.addCell("Social Studies\n");

        table.addCell("Thursday\n");
        table.addCell("Reading\n");
        table.addCell("Writing\n");
        table.addCell("Art\n");
        table.addCell("Music\n");

        table.addCell("Friday");
        table.addCell("Math\n");
        table.addCell("Science\n");
        table.addCell("English\n");
        table.addCell("Social Studies\n");

        // Add the table to the Document
        document.add(table);

        document.close();


        return outputStream;
    }

    private void tableToArray() {
        Stream stream1 = streamRepository.findAll().get(0);
        List<Timeslot> timeslots = timeslotRepository.findAll();

        List<DayOfWeek> days = timeslots.stream().map(
                timeslot -> timeslot.getId().getDay()
        ).toList();


        List<TimeSlotsMap> period = timeslots.stream()
                .map(
                        timeslot -> new TimeSlotsMap(timeslot.getId().getStartTime(), timeslot.getId().getEndTime())
                )
                .distinct()
                .toList();
        if (timeslots.stream().anyMatch(timeslot -> period.stream().anyMatch(localTimes -> localTimes.equals(timeslot)))) {
            out.println("okay");
        }


        String[][] table = new String[days.size()][period.size()];

        for (int i = 0; i < days.size(); i++) {
            for (int j = 0; j < period.size(); j++) {
                if (i == 0) {
                    if (j == 0) {
                        table[i][j] = "Day";
                    } else {

                        var start = period.get(j).startTime();
                        var end = period.get(j).endTime();


                        table[i][j] = start + "\n" + end;
                    }

                } else {
                    if (j == 0) {
                        table[i][j] = days.get(i).toString();
                    }

                    Timeslot specificTimeslot = timeslotRepository.findAllById_DayAndId_StartTimeAndId_EndTime(
                            days.get(i),
                            period.get(j).startTime(),
                            period.get(j).endTime()
                    ).orElse(null);
                    if (specificTimeslot==null){
                        table[i][j] = "";
                        continue;
                    }
                    List<Lesson> lessons = specificTimeslot.getLessons().stream()
                            .filter(lesson -> lesson.getId().getStream() == stream1).toList();
                    if (lessons.size() == 1) {
                        table[i][j] = lessons.get(0).getId().getSubject().toString();
                    } else {
                        table[i][j] = "";
                    }

                }

            }
        }
        getLogger("test").log(INFO, "test");
        out.print(Arrays.deepToString(table));
    }


}

