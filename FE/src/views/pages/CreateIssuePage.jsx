import React, { useReducer, useCallback } from "react";
import styled from "styled-components";
import Header from "@/common/Header";
import CustomizedDropdown from "@/common/Dropdown/Dropdown";
import User from "@/common/User";
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
    default:
      return state;
  }
};

const LabelData = {
  type: "wide",
  category: "labels",
  title: "Labels",
  openingCallback: "",
  closingCallback: "labels closing cb",
};

const MilestoneData = {
  type: "wide",
  category: "milestones",
  title: "Milestone",
  openingCallback: "",
  closingCallback: "milestone closing cb",
};

const CreateIssuePage = () => {
  const [issue, issueDispatch] = useReducer(issueReducer, initialState);

  const updateTitle = useCallback(
    (e) => issueDispatch({ type: issueActions.UPDATE_TITLE, payload: e.target.value }),
    []
  );

  const updateAssignees = useCallback(
    (newList) => issueDispatch({ type: issueActions.UPDATE_ASSIGNEES, payload: newList }),
    []
  );

  const AssigneeData = {
    type: "wide",
    category: "users",
    title: "Assignees",
    openingCallback: "",
    closingCallback: updateAssignees,
  };

  const handlers = {
    onClickCancel: null,
    onClickSubmit: null,
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
                <CommentEditor values={issue} handlers={handlers} />
              </EditorWrapper>
            </EditorColumn>
            <DropdownColumn>
              <DropdownWrapper>
                <CustomizedDropdown {...AssigneeData} />
                <SelectedItem>
                  {issue.assignees.map(({ nickname, profileImage }, i) => (
                    <User key={nickname + i} nickname={nickname} profileImage={profileImage} />
                  ))}
                </SelectedItem>
              </DropdownWrapper>
              <DropdownWrapper>
                <CustomizedDropdown {...LabelData} />
                <SelectedItem>FE</SelectedItem>
              </DropdownWrapper>
              <DropdownWrapper>
                <CustomizedDropdown {...MilestoneData} />
                <SelectedItem>Phase1</SelectedItem>
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
