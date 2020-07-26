import React, { useState, useEffect, useReducer } from "react";
import { useSelector } from "react-redux";
import styled, { css } from "styled-components";
import Button from "@/common/Button";
import Input from "@/common/Input";
import Label from "@/common/Label";
import { RetweetOutlined } from "@ant-design/icons";
import { generateRandomColor } from "Lib/utilFunctions";

const initialValues = {
  id: null,
  title: "",
  color: "",
  description: "",
};

const inputActions = {
  UPDATE_TITLE: "UPDATE_TITLE",
  UPDATE_DESCRIPTION: "UPDATE_DESCRIPTION",
  UPDATE_COLOR: "UPDATE_COLOR",
  RESET_VALUES: "RESET_VALUES",
};

const inputReducer = (state, { type, payload }) => {
  const { UPDATE_TITLE, UPDATE_DESCRIPTION, UPDATE_COLOR, RESET_VALUES } = inputActions;
  switch (type) {
    case UPDATE_TITLE:
      return { ...state, title: payload };
    case UPDATE_DESCRIPTION:
      return { ...state, description: payload };
    case UPDATE_COLOR:
      return { ...state, color: payload };
    case RESET_VALUES:
      return { ...state, title: "", description: "", color: generateRandomColor() };
    default:
      return state;
  }
};

const buttons = {
  create: "Create label",
  list: "Save changes",
};

const LabelEditor = ({ mode, values, handlers }) => {
  const [editing, setEditing] = useState(false);
  const [inputValues, inputDispatch] = useReducer(inputReducer, values || initialValues);
  const { id, title, color, description } = inputValues;
  const { CREATE_LABEL } = useSelector((state) => state.loading);

  useEffect(() => {
    if (!color) updateColor(generateRandomColor());
  }, []);

  const updateTitle = (title) => inputDispatch({ type: inputActions.UPDATE_TITLE, payload: title });
  const updateDescription = (description) =>
    inputDispatch({
      type: inputActions.UPDATE_DESCRIPTION,
      payload: description,
    });
  const updateColor = (labelColor) =>
    inputDispatch({ type: inputActions.UPDATE_COLOR, payload: labelColor });
  const resetValues = () => inputDispatch({ type: inputActions.RESET_VALUES });

  const handleConfirm = async () => {
    if (mode === "create") {
      await handlers.onClickCreate(inputValues);
      resetValues();
    } else {
      await handlers.onClickEdit(id, inputValues);
      setEditing(false);
    }
  };

  return (
    <Wrapper mode={mode}>
      <PreviewWrapper>
        <Label title={title || "Label preview"} color={color} />
        {mode === "list" && (
          <>
            {!editing && <Description>{description}</Description>}
            <ButtonWrapper>
              {!editing && <Button type="text" text="Edit" onClick={() => setEditing(true)} />}
              <Button type="text" text="Delete" onClick={() => handlers.onClickDelete(id)} />
            </ButtonWrapper>
          </>
        )}
      </PreviewWrapper>
      {(mode !== "list" || editing) && (
        <EditorWrapper>
          <InputWrapper>
            <StyledLabel>Label name</StyledLabel>
            <Input
              placeholder="Label name"
              value={title}
              onChange={(e) => updateTitle(e.target.value)}
            />
          </InputWrapper>
          <InputWrapper>
            <StyledLabel>Description</StyledLabel>
            <Input
              placeholder="Description (optional)"
              value={description}
              onChange={(e) => updateDescription(e.target.value)}
            />
          </InputWrapper>
          <InputWrapper>
            <StyledLabel>Color</StyledLabel>
            <ColorWrapper>
              <Refresh color={color} onClick={() => updateColor(generateRandomColor())}>
                <RetweetOutlined style={{ color: "#fff", fontWeight: "bold" }} />
              </Refresh>
              <Input style={{ flex: "1" }} value={color} readOnly />
            </ColorWrapper>
          </InputWrapper>
          <ButtonWrapper>
            <Button
              text="Cancel"
              onClick={() => (mode === "create" ? handlers.onClickCancel() : setEditing())}
            />
            <Button
              type="primary"
              text={buttons[mode]}
              onClick={handleConfirm}
              loading={CREATE_LABEL}
            />
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
  background: ${(props) => props.color};
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
