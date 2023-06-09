import { BasicScheduleDto } from "models/api/company/ScheduleDto";
import {
  DataContainer,
  InfoFree,
  InfoUsed,
  StyledHeading,
} from "components/common/schedule/Schedule.style";
import { formatTime } from "../common/Functions";
import { DoctorAppointment } from "components/doctor/appointment/DoctorAppointment";
import { DictionaryItems } from "../common/DictionaryItems";

interface CreateLineFromArrayProps {
  schedule: BasicScheduleDto;
  patientItems?: DictionaryItems;
  clinicItems?: DictionaryItems;
}

export const DoctorLineFromArray = (props: CreateLineFromArrayProps) => {
  return (
    <DataContainer key={props.schedule.uuid}>
      {!props.schedule.appointment ? (
        <InfoFree>
          {formatTime(props.schedule.startTime as Date) +
            " - " +
            formatTime(props.schedule.endTime as Date) +
            " (Wolne)"}
        </InfoFree>
      ) : (
        <InfoUsed>
          <StyledHeading>
            {formatTime(props.schedule.startTime as Date) +
              " - " +
              formatTime(props.schedule.endTime as Date) +
              " (Wizyta)"}
          </StyledHeading>
          <DoctorAppointment
            key={props.schedule.uuid}
            patients={props.patientItems as any}
            clinics={props.clinicItems as any}
            visible={false}
            appointmentUuid={props.schedule.appointment}
          />
        </InfoUsed>
      )}
    </DataContainer>
  );
};
