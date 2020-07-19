import React from "react";
import styled from "styled-components";
import Header from "@/common/Header";
import CustomizedDropdown from "@/common/CustomizedDropdown";
import Comment from "@/issues/Comment";
import { Avatar, Input } from "antd";

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

  return (
    <>
      <Header />
      <main>
        <div className="container">
          <Wrapper>
            <EditorColumn>
              <Avatar
                src="https://avatars1.githubusercontent.com/u/38597469?s=460&u=2dfb09e65b47940c7661b7093c6cf8c91b8f13ea&v=4"
                style={{ width: "44px", height: "44px", marginRight: "12px" }}
              />
              <EditorWrapper>
                <TitleWrppaer>
                  <Input placeholder="Title" />
                </TitleWrppaer>
                <Comment />
              </EditorWrapper>
            </EditorColumn>
            <DropdownColumn>
              <DropdownWrapper>
                <CustomizedDropdown {...AssigneeData} />
                <SelectedItem>reesekimm</SelectedItem>
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
    top: 7%;
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
    top: 7%;
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
