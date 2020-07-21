import React, { useState } from "react";
import styled, { css } from "styled-components";
import Button from "@/common/Button";
import Input from "@/common/Input";
import Label from "@/common/Label";
import { RetweetOutlined } from "@ant-design/icons";

const temp = {
  id: null,
  title: null,
  color: null,
  description: null,
};

const LabelEditor = ({ mode, values, handlers }) => {
  const [editing, setEditing] = useState(false);
  const { id, title, color, description } = values || temp;

  const buttons = {
    create: "Create label",
    list: "Save changes",
  };

  return (
    <Wrapper mode={mode}>
      <PreviewWrapper>
        <Label title={title || "Label preview"} color={color || "#7057FF"} />
        {mode === "list" && (
          <>
            {!editing && <Description>{description}</Description>}
            <ButtonWrapper>
              {!editing && <Button type="text" text="Edit" onClick={() => setEditing(true)} />}
              <Button type="text" text="Delete" />
            </ButtonWrapper>
          </>
        )}
      </PreviewWrapper>
      {(mode !== "list" || editing) && (
        <EditorWrapper>
          <InputWrapper>
            <StyledLabel>Label name</StyledLabel>
            <Input placeholder="Label name" defaultValue={title} />
          </InputWrapper>
          <InputWrapper>
            <StyledLabel>Description</StyledLabel>
            <Input placeholder="Description (optional)" defaultValue={description} />
          </InputWrapper>
          <InputWrapper>
            <StyledLabel>Color</StyledLabel>
            <ColorWrapper>
              <Refresh>
                <RetweetOutlined style={{ color: "#fff", fontWeight: "bold" }} />
              </Refresh>
              <Input style={{ flex: "1" }} defaultValue={color} />
            </ColorWrapper>
          </InputWrapper>
          <ButtonWrapper>
            <Button
              text="Cancel"
              onClick={() => (mode === "create" ? handlers.onClickCancel() : setEditing())}
            />
            <Button type="primary" text={buttons[mode]} />
          </ButtonWrapper>
        </EditorWrapper>
      )}
    </Wrapper>
  );
};

export default LabelEditor;

const Wrapper = styled.div`
  width: 100%;
  background: #fff;

  ${({ mode }) =>
    mode === "create"
      ? css`
          background: #fafbfc;
          border-radius: 5px;
          border: 1px solid #e1e4e8;
        `
      : css`
          border-top: 1px solid #e1e4e8;
        `};
`;

const PreviewWrapper = styled.div`
  width: 100%;
  padding: ${({ mode }) => (mode === "create" ? "16px 20px" : "20px")};
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const Description = styled.div`
  flex: 1;
  margin-left: 10px;
  font-size: 13px;
`;

const EditorWrapper = styled.div`
  width: 100%;
  height: 70px;
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
  padding: ${(props) => (props.mode === "list" ? "20px" : "8px 20px 20px")};
`;

const InputWrapper = styled.div`
  width: 26%;
`;

const StyledLabel = styled.label`
  display: block;
  margin-bottom: 5px;
  font-size: 12px;
  font-weight: bold;
`;

const ColorWrapper = styled.div`
  display: flex;
`;

const Refresh = styled.div`
  width: 32px;
  height: 32px;
  border-radius: 5px;
  background: #c6ddff;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 5px;
  cursor: pointer;
`;

const ButtonWrapper = styled.div`
  height: 100%;
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
  button:not(:last-child) {
    margin-right: 5px;
  }
`;
