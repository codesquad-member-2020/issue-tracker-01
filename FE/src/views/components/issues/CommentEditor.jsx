import React, { useState, useCallback } from "react";
import ReactMarkdown from "react-markdown";
import { CodeBlock, InlineCodeBlock, BlockQuote, LinkRenderer } from "Styles/MarkdownStyle";
import styled from "styled-components";
import Button from "@/common/Button";

const CommentEditor = ({ type, values, handlers }) => {
  const [isPreview, setIsPreview] = useState(false);
  const [comment, setComment] = useState("");

  const makeButtons = useCallback(
    (type) => {
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
              <Button type="text" text="Cancel" onClick={handlers.onClickCancel} />
              <Button
                type="primary"
                text="Submit new issue"
                onClick={() => console.log({ ...values, comment })}
              />
            </>
          );
      }
    },
    [type, values, comment]
  );

  return (
    <CommentWrapper>
      <CommentTab>
        <Button type="text" text="Write" onClick={() => setIsPreview(false)} />
        <Button type="text" text="Preview" onClick={() => setIsPreview(true)} />
      </CommentTab>
      <CommentArea>
        {!isPreview && (
          <Textarea value={comment} onChange={(e) => setComment(e.currentTarget.value)}></Textarea>
        )}
        {isPreview && (
          <Preview>
            <ReactMarkdown
              source={comment}
              skipHtml={false}
              escapeHtml={false}
              renderers={{
                code: CodeBlock,
                inlineCode: InlineCodeBlock,
                blockquote: BlockQuote,
                link: LinkRenderer,
              }}
            />
          </Preview>
        )}
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
  background: #fafbfc;
  border-radius: 2px;
  border: 1px solid lightgray;
`;

const Preview = styled.div`
  width: 100%;
  min-height: 160px;
  max-height: 440px;
  margin-bottom: 12px;
  padding: 5px;
  border-bottom: 1px solid lightgray;
  overflow-x: hidden;
  overflow-y: scroll;
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
