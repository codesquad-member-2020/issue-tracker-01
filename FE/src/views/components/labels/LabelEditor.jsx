import React from "react";
import styled from "styled-components";
import Button from "@/common/Button";
import Input from "@/common/Input";
// import { Input } from "antd";
import { RetweetOutlined } from "@ant-design/icons";

const LabelEditor = ({ mode }) => {
  const buttons = {
    create: "Create label",
    edit: "Save changes",
  };

  return (
    <EditorWrapper mode={mode}>
      <InputWrapper>
        <StyledLabel>Label name</StyledLabel>
        <Input placeholder="Label name" />
      </InputWrapper>
      <InputWrapper>
        <StyledLabel>Description</StyledLabel>
        <Input placeholder="Description (optional)" />
      </InputWrapper>
      <InputWrapper>
        <StyledLabel>Color</StyledLabel>
        <ColorWrapper>
          <Refresh>
            <RetweetOutlined style={{ color: "#fff", fontWeight: "bold" }} />
          </Refresh>
          <Input style={{ flex: "1" }} />
        </ColorWrapper>
      </InputWrapper>
      <ButtonWrapper>
        <Button text="Cancel" />
        <Button type="primary" text={buttons[mode]} />
      </ButtonWrapper>
    </EditorWrapper>
  );
};

export default LabelEditor;

const EditorWrapper = styled.div`
  width: 100%;
  height: 70px;
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
  padding: ${(props) => (props.mode === "edit" ? "8px 20px 16px" : "20px")};
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
