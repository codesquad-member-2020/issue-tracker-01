import React from "react";
import styled from "styled-components";
import Button from "@/common/Button";

const makeButtons = (type) => {
  switch (type) {
    case "edit-comment":
      return (
        <>
          <Button text="Cancel" />
          <Button type="primary" text="Update comment" />
        </>
      );
    case "create-comment":
      return (
        <>
          <Button text="Cancel" />
          <Button type="primary" text="Comment" />
        </>
      );
    default:
      return (
        <>
          <Button type="text" text="Cancel" />
          <Button type="primary" text="Submit new issue" />
        </>
      );
  }
};

const CommentEditor = ({ type }) => {
  return (
    <CommentWrapper>
      <CommentTab>
        <Button type="text" text="Write" />
        <Button type="text" text="Preview" />
      </CommentTab>
      <CommentArea>
        <Textarea></Textarea>
      </CommentArea>
      <ButtonWrapper type={type}>{makeButtons(type)}</ButtonWrapper>
    </CommentWrapper>
  );
};

export default CommentEditor;

const CommentWrapper = styled.div`
  height: fit-content;
`;

const CommentTab = styled.div`
  display: flex;
  margin-bottom: 8px;
`;

const CommentArea = styled.div``;

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
  justify-content: ${(props) => (props.type === "create-issue" ? "space-between" : "flex-end")};
  margin-bottom: 12px;
`;
