import React from "react";
import styled from "styled-components";
import Header from "@/common/Header";
import Button from "@/common/Button";
import { Avatar, Input } from "antd";

const CreateIssuePage = () => {
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
                <CommentWrapper>
                  <CommentTab>
                    <Button type="text" text="Write" />
                    <Button type="text" text="Preview" />
                  </CommentTab>
                  <Comment>
                    <Textarea></Textarea>
                  </Comment>
                  <ButtonWrapper>
                    <Button type="text" text="Cancel" />
                    <Button type="primary" text="Submit new issue" />
                  </ButtonWrapper>
                </CommentWrapper>
              </EditorWrapper>
            </EditorColumn>
            <DropdownColumn>Put Dropdown menus here!</DropdownColumn>
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

const CommentWrapper = styled.div`
  height: fit-content;
`;

const CommentTab = styled.div`
  display: flex;
  margin-bottom: 8px;
`;

const Comment = styled.div``;

const Textarea = styled.textarea`
  width: 100%;
  min-height: 160px;
  max-height: 440px;
  margin-bottom: 12px;
`;

const Tab = styled.div`
  :not(:last-child) {
    margin-right: 10px;
  }
`;

const ButtonWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 12px;
`;
