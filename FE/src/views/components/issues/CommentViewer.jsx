import React from "react";
import styled from "styled-components";
import Button from "@/common/Button";
import { Avatar, Input } from "antd";
import { getRelativeTime } from "Utils/utilFunctions";

const CommentViewer = ({ author, ...comment }) => {
  const { description, createdAt, updatedAt, images, writer } = comment;
  return (
    <CommentWrapper>
      <Avatar
        src="https://avatars1.githubusercontent.com/u/38597469?s=460&u=2dfb09e65b47940c7661b7093c6cf8c91b8f13ea&v=4"
        style={{ width: "44px", height: "44px", marginRight: "12px" }}
      />
      <Comment author={author.nickname} writer={writer.nickname}>
        <Detail author={author.nickname} writer={writer.nickname}>
          <span>
            <Writer>{writer.nickname}</Writer> commented {getRelativeTime(createdAt)} ago
          </span>
          {author.nickname === writer.nickname && <Button type="text" text="Edit" />}
        </Detail>
        <Description>{description}</Description>
      </Comment>
    </CommentWrapper>
  );
};

export default CommentViewer;

const CommentWrapper = styled.div`
  display: flex;
  margin: 20px 0;
`;

const Comment = styled.div`
  flex: 1;
  border: 1px solid lightgray;
  border-radius: 5px;
  position: relative;
  &:after {
    content: "";
    position: absolute;
    border-style: solid;
    border-width: 10px 10px 10px 0;
    border-color: transparent ${(props) => (props.author === props.writer ? "#F1F8FF" : "#FAFBFC")};
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

const Writer = styled.span`
  font-weight: bold;
`;

const Detail = styled.div`
  background: ${(props) => (props.author === props.writer ? "#F1F8FF" : "#FAFBFC")};
  border-bottom: 1px solid lightgray;
  border-radius: 5px 5px 0 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  min-height: 48px;
  padding: 0 12px;
`;

const Description = styled.div`
  padding: 12px;
`;
