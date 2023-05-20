import styled from "styled-components";
import {ADMIN_FONT_COLOR, DOCTOR_FONT_COLOR, FONT_COLOR} from "constants/constants";

export const MainPanelContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 25px;
`;

export const MainPanelWrapper = styled.div`
  display: flex;
  width: 1000px;
  min-height: 50vh;
  padding: 16px;
  border-radius: 8px;
  background-color: #ffffff;
  justify-content: center;
  align-items: center;
`;

export const DoctorHeader = styled.span`
  display: flex;
  justify-content: center;
  font-size: 36px;
  font-weight: 600;
  margin-top: 64px;
  margin-bottom: 25px;
  color: ${DOCTOR_FONT_COLOR};
`;
