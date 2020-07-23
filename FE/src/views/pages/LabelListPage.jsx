import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { fetchLabels } from "Store/label/labelAction";
import styled from "styled-components";
import Header from "@/common/Header";
import NavButtons from "@/common/NavButtons";
import Button from "@/common/Button";
import LabelEditor from "@/labels/LabelEditor";
import { TagOutlined, FlagOutlined } from "@ant-design/icons";

const LabelListPage = () => {
  const [creating, setCreating] = useState(false);
  const { loading, error, labels } = useSelector((state) => state.label);
  const dispatch = useDispatch();

  useEffect(() => {
    fetchLabels()(dispatch);
  }, [dispatch]);

  const handlersForCreateMode = {
    onClickCancel: setCreating,
    onClickConfirm: null,
  };

  const handlersForListMode = {
    onClickDelete: null,
    onClickConfirm: null,
  };

  return (
    <>
      <Header />
      <main>
        <div className="container">
          <Nav>
            <NavButtons
              menus={[
                { icon: <TagOutlined />, title: "Labels", param: "/labels" },
                { icon: <FlagOutlined />, title: "Milestones", param: "/milestones" },
              ]}
            />
            <Button type="primary" text="New label" onClick={() => setCreating(!creating)} />
          </Nav>
          {creating && <LabelEditor mode="create" handlers={handlersForCreateMode} />}
          <LabelListWrapper>
            <LabelListHeader>
              {labels && labels.length > 0 ? labels.length : 0} labels
            </LabelListHeader>
            {loading ? (
              <h2>loading...</h2>
            ) : error ? (
              <h2>error</h2>
            ) : labels.length > 0 ? (
              <LabelListBody>
                {labels.map((item) => (
                  <LabelEditor
                    key={item.id}
                    mode="list"
                    values={item}
                    handlers={handlersForListMode}
                  />
                ))}
              </LabelListBody>
            ) : (
              <h2>Create your first label!</h2>
            )}
          </LabelListWrapper>
        </div>
      </main>
    </>
  );
};

export default LabelListPage;

const Nav = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 16px;
`;

const LabelListWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  border: 1px solid #e1e4e8;
  border-radius: 5px;
  margin-top: 16px;
`;

const LabelListHeader = styled.div`
  padding: 20px;
  background: #fafbfc;
  width: 100%;
  border-radius: 5px 5px 0 0;
  border-bottom: none;
  font-weight: bold;
`;

const LabelListBody = styled.div`
  width: 100%;
`;
