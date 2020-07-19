import React from "react";
import styled from "styled-components";
import Button from "@/common/Button";

const Comment = () => {
  return (
    <CommentWrapper>
      <CommentTab>
        <Button type="text" text="Write" />
        <Button type="text" text="Preview" />
      </CommentTab>
      <CommentArea>
        <Textarea></Textarea>
      </CommentArea>
      <ButtonWrapper>
        <Button type="text" text="Cancel" />
        <Button type="primary" text="Submit new issue" />
      </ButtonWrapper>
    </CommentWrapper>
  );
};

export default Comment;

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
  justify-content: space-between;
  margin-bottom: 12px;
`;
