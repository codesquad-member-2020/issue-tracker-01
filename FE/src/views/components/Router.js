import React from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";

const LoginPage = () => <div>LoginPage</div>;
const IssueListPage = () => <div>IssueListPage</div>;
const IssueDetailPage = () => <div>IssueDetailPage</div>;
const CreateIssuePage = () => <div>CreateIssuePage</div>;
const MilestoneListPage = () => <div>MilestoneListPage</div>;
const CreateMilestonePage = () => <div>CreateMilestonePage</div>;
const EditMilestonePage = () => <div>EditMilestonePage</div>;
const LabelListPage = () => <div>LabelListPage</div>;
const NotFoundPage = () => <div>404</div>;

export default () => (
  <BrowserRouter>
    <Switch>
      <Route path="/" exact component={LoginPage} />
      <Route path="/issues" component={IssueListPage} />
      <Route path="/issues/:id" component={IssueDetailPage} />
      <Route path="/issues/new" component={CreateIssuePage} />
      <Route path="/milestones" component={MilestoneListPage} />
      <Route path="/milestones/new " component={CreateMilestonePage} />
      <Route path="/milestones/:id/edit" component={EditMilestonePage} />
      <Route path="/labels" component={LabelListPage} />
      <Route component={NotFoundPage} />
    </Switch>
  </BrowserRouter>
);
