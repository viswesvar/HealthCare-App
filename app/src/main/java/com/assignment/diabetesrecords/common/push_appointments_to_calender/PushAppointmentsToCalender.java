package com.assignment.diabetesrecords.common.push_appointments_to_calender;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.CalendarContract;

/**
 * Created by lokendra on 9/15/16.
 */
public class PushAppointmentsToCalender {

//public static long pushAppointmentsToCalender(AppCompatActivity curActivity, String title, String addInfo, String place, int status, long startDate, long endDate, boolean needReminder, boolean needMailService, int iAction, String existingEventId) {

    public static long pushAppointmentsToCalender(Context curActivity, String title, String addInfo, String place, int status, long startDate, long endDate, boolean needReminder, boolean needMailService, int iAction, String existingEventId, boolean freqDaily, boolean freqWeekly, String EndDateYYYYMMDD, String weekDay, String a, String b)

    {
        /***************** Event: note(without alert) *******************/

        Boolean isUpdate=false;


        for(int i=105; i<135; i++)
        {
            deleteAppointmentsToCalender(curActivity, String.valueOf(i));
        }

        //  String existingEventId = "93";
        String eventUriString = "content://com.android.calendar/events";
        ContentValues eventValues = new ContentValues();

        if (iAction == 0)
        {
            isUpdate = false;

        }
        else if(iAction ==1 )
        {
            isUpdate = true;

        }

        eventValues.put("calendar_id", 1); // id, We need to choose from
        // our mobile for primary
        // its 1
        eventValues.put("title", title);
        eventValues.put("description", addInfo);
        eventValues.put("eventLocation", place);

        // long endDate = startDate + 1000 * 60 * 60; // For next 1hr

        eventValues.put("dtstart", startDate);

        eventValues.put("allDay", false);

        if(freqDaily == true)
        {
            eventValues.put(CalendarContract.Events.DURATION, "+P30M");
//+P5W3D  --represents 5 weeks and 3 days... similarly we can have H for hours, M for minutes and S for seconds.
            //We cannot mention month or year as duration directly...

            eventValues.put("rrule", "FREQ=DAILY;UNTIL=" + EndDateYYYYMMDD +";BYDAY=SU,MO,TU,WE,TH,FR,SA");
            //UNTIL accepts date im "YYYYMMDD" format example 31st Dec 2013 will be "20131231"
        }
        else if(freqWeekly == true)
        {
            eventValues.put(CalendarContract.Events.DURATION, "+P30M");
            //rrule: FREQ=WEEKLY;BYDAY=MO,TU,WE,TH,FR
            eventValues.put("rrule", "FREQ=WEEKLY;WKST=SU;UNTIL="+EndDateYYYYMMDD +";BYDAY="+weekDay);
          //  eventValues.put(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startDate);
        } else {
              eventValues.put("dtend", startDate);
        }


        // values.put("allDay", 1); //If it is bithday alarm or such
        // kind (which should remind me for whole day) 0 for false, 1
        // for true
        eventValues.put("eventStatus", status); // This information is
        // sufficient for most
        // entries tentative (0),
        // confirmed (1) or canceled
        // (2):
        eventValues.put("eventTimezone", "UTC/GMT +5:30");
   /*Comment below visibility and transparency  column to avoid java.lang.IllegalArgumentException column visibility is invalid error */

    /*eventValues.put("visibility", 3); // visibility to default (0),
                                        // confidential (1), private
                                        // (2), or public (3):
    eventValues.put("transparency", 0); // You can control whether
                                        // an event consumes time
                                        // opaque (0) or transparent
                                        // (1).
      */
        eventValues.put("hasAlarm", 1); // 0 for false, 1 for true
        long eventID=0;
        try
        {
            if(isUpdate == true)
            {
                eventValues.put(CalendarContract.Events._ID, existingEventId);
                //--------------
                ContentResolver cr = curActivity.getApplicationContext().getContentResolver();


                // ContentValues values = new ContentValues();
                // values.put (CalendarContract.Events.CALENDAR_ID, 1);
                //  values.put (CalendarContract.Events.TITLE, title);
                //  values.put (CalendarContract.Events.DTEND, eventEnd.getTimeInMillis());
                String where = "_id =" + existingEventId +
                        " and " + "CALENDAR_ID" + "=" + 1;
                int count = cr.update (CalendarContract.Events.CONTENT_URI, eventValues, where, null);
                if (count != 1)
                    throw new IllegalStateException ("more than one row updated");
                //-----------------
            }
            else
            {
                Uri eventUri = curActivity.getApplicationContext().getContentResolver().insert(Uri.parse(eventUriString), eventValues);
                eventID = Long.parseLong(eventUri.getLastPathSegment());
            }



        }
        catch (SecurityException se)
        {
            se.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if (needReminder) {
            /***************** Event: Reminder(with alert) Adding reminder to event *******************/

            if(isUpdate == true)
            {
                eventID = Integer.valueOf(existingEventId);
            }

            String reminderUriString = "content://com.android.calendar/reminders";

            ContentValues reminderValues = new ContentValues();

            reminderValues.put("event_id", eventID);
            reminderValues.put("minutes", 15); // Default value of the
            // system. Minutes is a
            // integer
            reminderValues.put("method", 1); // Alert Methods: Default(0),
            // Alert(1), Email(2),
            // SMS(3)


            try
            {
                if (isUpdate == true)
                {
/*
                    //--------------
                    ContentResolver cr = curActivity.getApplicationContext().getContentResolver();
                    // ContentValues values = new ContentValues();
                    // values.put (CalendarContract.Events.CALENDAR_ID, 1);
                    //  values.put (CalendarContract.Events.TITLE, title);
                    //  values.put (CalendarContract.Events.DTEND, eventEnd.getTimeInMillis());
                    String where = "_id =" + existingEventId ;
                     //   +    " and " + "CALENDAR_ID" + "=" + 1;
                    int count = cr.update (CalendarContract.Reminders.CONTENT_URI, reminderValues, where, null);
                    if (count != 1)
                        throw new IllegalStateException ("more than one row updated");
                    //-----------------
*/

                }
                else if (isUpdate == false)
                {
                    Uri reminderUri = curActivity.getApplicationContext().getContentResolver().insert(Uri.parse(reminderUriString), reminderValues);

                }

            }
            catch (SecurityException se)
            {
                se.printStackTrace();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }

        /***************** Event: Meeting(without alert) Adding Attendies to the meeting *******************/

        if (needMailService) {
            String attendeuesesUriString = "content://com.android.calendar/attendees";

            /********
             * To add multiple attendees need to insert ContentValues multiple
             * times
             ***********/
            ContentValues attendeesValues = new ContentValues();

            attendeesValues.put("event_id", eventID);
            attendeesValues.put("attendeeName", "xxxxx"); // Attendees name
            attendeesValues.put("attendeeEmail", "yyyy@gmail.com");// Attendee
            // E
            // mail
            // id
            attendeesValues.put("attendeeRelationship", 0); // Relationship_Attendee(1),
            // Relationship_None(0),
            // Organizer(2),
            // Performer(3),
            // Speaker(4)
            attendeesValues.put("attendeeType", 0); // None(0), Optional(1),
            // Required(2), Resource(3)
            attendeesValues.put("attendeeStatus", 0); // NOne(0), Accepted(1),
            // Decline(2),
            // Invited(3),
            // Tentative(4)

            try
            {
                Uri attendeuesesUri = curActivity.getApplicationContext().getContentResolver().insert(Uri.parse(attendeuesesUriString), attendeesValues);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }

        return eventID;

    }


    public static void deleteAppointmentsToCalender(Context curActivity,  String existingEventId) {

        try
        {
                ContentResolver cr = curActivity.getApplicationContext().getContentResolver();

                String where = "_id =" + existingEventId +
                        " and " + "CALENDAR_ID" + "=" + 1;
                int count = cr.delete (CalendarContract.Events.CONTENT_URI, where,  null);
                if (count != 1)
                    throw new IllegalStateException ("more than one row updated");

        }
        catch (SecurityException se)
        {
            se.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }




}
