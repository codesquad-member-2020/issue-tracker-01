import React from "react";
import styled from "styled-components";
import Header from "@/common/Header";
import Label from "@/common/Label";
import Button from "@/common/Button";
import CustomizedDropdown from "@/common/CustomizedDropdown";
import User from "@/common/User";
import CommentViewer from "@/issues/CommentViewer";
import CommentEditor from "@/issues/CommentEditor";
import { getRelativeTime } from "Utils/utilFunctions";
import { detail } from "Assets/mockIssue";

const CreateIssuePage = () => {
  const AssigneeData = {
    type: "wide",
    title: "Assignee",
    itemList: ["reesekimm", "alex"],
    onSelect: null,
  };

  const LabelData = {
    type: "wide",
    title: "Label",
    itemList: ["FE", "BE"],
    onSelect: null,
  };

  const MilestoneData = {
    type: "wide",
    title: "Milestones",
    itemList: ["Phase1", "Phase2"],
    onSelect: null,
  };

  const {
    issueNumber,
    title,
    createdAt,
    updatedAt,
    author,
    assignees,
    labels,
    milestone,
    comments,
    opened,
  } = detail;

  return (
    <>
      <Header />
      <main>
        <div className="container">
          <div>
            <TitleWrapper>
              <h1>
                <Title>{title}</Title>
                <IssueNumber>#{issueNumber}</IssueNumber>
              </h1>
              <Button text="Edit" />
            </TitleWrapper>
            <Summary>
              {opened ? (
                <Label title="opened" color="#28A745" />
              ) : (
                <Label title="closed" color="#D73A49" />
              )}
              <span>
                <Author>{author.nickname}</Author> opened this issues {getRelativeTime(createdAt)}{" "}
                ago · {comments.length} comments
              </span>
            </Summary>
          </div>
          <Wrapper>
            <EditorColumn>
              <CommentList>
                {comments.length > 0 &&
                  comments.map((comment, i) => (
                    <CommentViewer key={comment.createdAt + i} author={author} {...comment} />
                  ))}
              </CommentList>
              <CommentEditor type="create-comment" />
            </EditorColumn>
            <DropdownColumn>
              <DropdownWrapper>
                <CustomizedDropdown {...AssigneeData} />
                <SelectedItem>
                  {assignees.length > 0 &&
                    assignees.map(({ nickname, profileImage }) => (
                      <User key={nickname} nickname={nickname} profileImage={profileImage} />
                    ))}
                </SelectedItem>
              </DropdownWrapper>
              <DropdownWrapper>
                <CustomizedDropdown {...LabelData} />
                <SelectedItem>
                  {labels.length > 0 &&
                    labels.map(({ title, color }) => (
                      <Label key={title} title={title} color={color} />
                    ))}
                </SelectedItem>
              </DropdownWrapper>
              <DropdownWrapper>
                <CustomizedDropdown {...MilestoneData} />
                <SelectedItem>{milestone.title}</SelectedItem>
              </DropdownWrapper>
            </DropdownColumn>
          </Wrapper>
        </div>
      </main>
    </>
  );
};

export default CreateIssuePage;

const TitleWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 4px;
`;

const Title = styled.span`
  font-size: 24px;
  font-weight: bold;
  margin-right: 10px;
`;

const IssueNumber = styled.span`
  font-size: 24px;
`;

const Summary = styled.div`
  display: flex;
  align-items: center;
  padding-bottom: 16px;
  border-bottom: 1px solid lightgray;
  margin-bottom: 20px;
`;

const Author = styled.span`
  font-weight: bold;
`;

const Wrapper = styled.div`
  display: flex;
  height: 100%;
  & > div {
    height: fit-content;
  }
`;

const EditorColumn = styled.div`
  width: 70%;
  margin-right: 20px;
`;

const CommentList = styled.div`
  border-bottom: 1px solid lightgray;
  padding-bottom: 20px;
  margin-bottom: 20px;
`;

const DropdownColumn = styled.div`
  flex: 1;
  padding: 12px;
`;

const DropdownWrapper = styled.div`
  padding: 12px 0;
  border-bottom: 1px solid lightgray;
`;

const SelectedItem = styled.div`
  margin-top: 12px;
`;
