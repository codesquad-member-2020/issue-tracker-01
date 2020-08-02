import React, { useReducer, useCallback } from "react";
import { useHistory } from "react-router-dom";
import { useDispatch } from "react-redux";
import { createIssue } from "Store/issue/issueAction";
import styled from "styled-components";
import Header from "@/common/Header";
import CustomizedDropdown from "@/common/Dropdown/Dropdown";
import User from "@/common/User";
import Label from "@/common/Label";
import CommentEditor from "@/issues/CommentEditor";
import { Avatar, Input } from "antd";

const initialState = {
  title: "",
  assignees: [],
  labels: [],
  milestone: null,
};

const issueActions = {
  UPDATE_TITLE: "UPDATE_TITLE",
  UPDATE_ASSIGNEES: "UPDATE_ASSIGNEES",
  UPDATE_LABELS: "UPDATE_LABELS",
  UPDATE_MILESTONE: "UPDATE_MILESTONE",
};

const issueReducer = (state, { type, payload }) => {
  const { UPDATE_TITLE, UPDATE_ASSIGNEES, UPDATE_LABELS, UPDATE_MILESTONE } = issueActions;
  switch (type) {
    case UPDATE_TITLE:
      return {
        ...state,
        title: payload,
      };
    case UPDATE_ASSIGNEES:
      return {
        ...state,
        assignees: payload,
      };
    case UPDATE_LABELS:
      return {
        ...state,
        labels: payload,
      };
    case UPDATE_MILESTONE:
      return {
        ...state,
        milestone: payload,
      };
    default:
      return state;
  }
};

const CreateIssuePage = () => {
  const [issue, issueDispatch] = useReducer(issueReducer, initialState);
  const { title, assignees, labels, milestone } = issue;
  const history = useHistory();
  const dispatch = useDispatch();

  const updateTitle = useCallback(
    ({ target: { value } }) => issueDispatch({ type: issueActions.UPDATE_TITLE, payload: value }),
    []
  );

  const updateAssignees = useCallback(
    (newList) => issueDispatch({ type: issueActions.UPDATE_ASSIGNEES, payload: newList }),
    []
  );

  const updateLabels = useCallback(
    (newList) => issueDispatch({ type: issueActions.UPDATE_LABELS, payload: newList }),
    []
  );

  const updateMilestone = useCallback(
    (newMilestone) => issueDispatch({ type: issueActions.UPDATE_MILESTONE, payload: newMilestone }),
    []
  );

  const AssigneeData = {
    type: "wide",
    category: "users",
    title: "Assignees",
    openingCallback: "",
    closingCallback: updateAssignees,
  };

  const LabelData = {
    type: "wide",
    category: "labels",
    title: "Labels",
    openingCallback: "",
    closingCallback: updateLabels,
  };

  const MilestoneData = {
    type: "wide",
    category: "milestones",
    title: "Milestone",
    openingCallback: "",
    closingCallback: updateMilestone,
  };

  const handlers = {
    onClickCancel: () => history.push("/issues"),
    onClickSubmit: (issue) => createIssue(issue)(dispatch),
  };

  return (
    <>
      <Header />
      <main>
        <div className="container">
          <Wrapper>
            <EditorColumn>
              <Avatar
                src="https://avatars0.githubusercontent.com/u/42695954?s=460&u=5227f8eb42e141c22cbffc2cc813e4d8ba2a9fd2&v=4"
                style={{ width: "44px", height: "44px", marginRight: "12px" }}
              />
              <EditorWrapper>
                <TitleWrppaer>
                  <Input placeholder="Title" onChange={updateTitle} />
                </TitleWrppaer>
                <CommentEditor issueData={issue} handlers={handlers} />
              </EditorWrapper>
            </EditorColumn>
            <DropdownColumn>
              <DropdownWrapper>
                <CustomizedDropdown {...AssigneeData} />
                <SelectedItem>
                  {assignees.map(({ userId, nickname, profileImage }) => (
                    <User key={userId} nickname={nickname} profileImage={profileImage} />
                  ))}
                </SelectedItem>
              </DropdownWrapper>
              <DropdownWrapper>
                <CustomizedDropdown {...LabelData} />
                <SelectedItem>
                  {labels.map(({ id, title, color }) => (
                    <Label key={id} title={title} color={color} />
                  ))}
                </SelectedItem>
              </DropdownWrapper>
              <DropdownWrapper>
                <CustomizedDropdown {...MilestoneData} />
                <SelectedItem>
                  <Milestone>{milestone ? milestone[0].title : ""}</Milestone>
                </SelectedItem>
              </DropdownWrapper>
            </DropdownColumn>
          </Wrapper>
        </div>
      </main>
    </>
  );
};

export default CreateIssuePage;

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
  display: flex;
`;

const EditorWrapper = styled.div`
  flex: 1;
  padding: 0 12px;
  border: 1px solid lightgray;
  border-radius: 5px;
  position: relative;
  &:after {
    content: "";
    position: absolute;
    border-style: solid;
    border-width: 10px 10px 10px 0;
    border-color: transparent #fff;
    display: block;
    width: 0;
    z-index: 1;
    left: -9px;
    top: 20px;
    margin-top: -10px;
  }
  &:before {
    content: "";
    position: absolute;
    border-style: solid;
    border-width: 10px 10px 10px 0;
    border-color: transparent lightgray;
    display: block;
    width: 0;
    z-index: 0;
    left: -10px;
    top: 20px;
    margin-top: -10px;
  }
`;

const TitleWrppaer = styled.div`
  margin: 12px 0;
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

const Milestone = styled.span`
  font-weight: 600;
`;
