import React, { useState } from "react";
import styled from "styled-components";
import Label from "@/common/Label";
import Button from "@/common/Button";
import LabelEditor from "@/labels/LabelEditor";

const LabelItem = (props) => {
  const [editing, setEditing] = useState(false);
  const { id, title, color, description } = props;

  return (
    <div>
      <Wrapper>
        <LabelWrapper>
          <Label title={title} color={color} />
        </LabelWrapper>
        <Description>{description}</Description>
        <ButtonWrapper>
          {!editing && <Button type="text" text="Edit" onClick={() => setEditing(true)} />}
          <Button type="text" text="Delete" />
        </ButtonWrapper>
      </Wrapper>
      {editing && <LabelEditor mode="edit" />}
    </div>
  );
};

export default LabelItem;

const Wrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 20px;
  border-top: 1px solid #e1e4e8;
  font-size: 12px;
`;

const LabelWrapper = styled.div`
  width: 20%;
`;

const Description = styled.div`
  flex: 1;
`;

const ButtonWrapper = styled.div`
  display: flex;
`;
