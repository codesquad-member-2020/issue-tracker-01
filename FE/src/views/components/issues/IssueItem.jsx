import React from "react";
import { Checkbox, Avatar } from "antd";
import { ExclamationCircleOutlined, FlagOutlined, UserOutlined } from "@ant-design/icons";
import styled from "styled-components";
import Label from "@/common/Label";
import { getRelativeTime } from "Utils/utilFunctions";

const IssueItem = (props) => {
  const { issueNumber, title, createdAt, author, assignees, labels, milestone, opened } = props;
  return (
    <StyledIssueItem>
      <Checkbox />
      <ExclamationCircleOutlined
        style={{ marginLeft: "10px", color: opened ? "#2EA44F" : "#D73A4A" }}
      />
      <ContentsWrapper>
        <TitleWrapper>
          <Title>{title}</Title>
          <LabelWrapper>
            {labels.map(({ title, color }) => (
              <Label key={title} title={title} color={color} />
            ))}
          </LabelWrapper>
        </TitleWrapper>
        <Details>
          #{issueNumber} opened {getRelativeTime(createdAt)} ago by {author.nickname}
          <MilestoneWrapper>
            <FlagOutlined style={{ marginRight: "5px" }} />
            {milestone.title}
          </MilestoneWrapper>
        </Details>
      </ContentsWrapper>
      <AssigneesWrapper>
        {assignees.map(({ nickname, profileImage }) => (
          <Avatar
            key={nickname}
            src={profileImage}
            icon={profileImage ? "" : <UserOutlined />}
            size="small"
            style={{ margin: "0 2px" }}
          />
        ))}
      </AssigneesWrapper>
    </StyledIssueItem>
  );
};

export default IssueItem;

const StyledIssueItem = styled.div`
  padding: 15px 12px;
  border-top: 1px solid #e1e4e8;
  display: flex;
  align-items: flex-start;
`;

const ContentsWrapper = styled.div`
  margin-left: 8px;
  margin-right: auto;
  color: #898989;
`;

const TitleWrapper = styled.div`
  font-weight: bold;
  display: flex;
  align-items: flex-start;
  flex-wrap: wrap;
  height: 22px;
`;

const Title = styled.span`
  font-size: 16px;
  margin-right: 10px;
`;

const LabelWrapper = styled.div`
  display: flex;
`;

const Details = styled.span`
  font-size: 12px;
`;

const AssigneesWrapper = styled.div`
  margin-right: 10%;
`;

const MilestoneWrapper = styled.span`
  margin-left: 10px;
`;
