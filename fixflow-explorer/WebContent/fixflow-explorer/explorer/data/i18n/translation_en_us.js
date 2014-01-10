/**
 * @author willi.tscheschner
 *
 * contains all strings for default language (en_us)
 *
 */



// namespace
if(window.Signavio == undefined) Signavio = {};
if(window.Signavio.I18N == undefined) Signavio.I18N = {};

Signavio.I18N.Language = "en_us"; //Pattern <ISO language code>_<ISO country code> in lower case!

Signavio.I18N.en_us = "English";
Signavio.I18N.de = "Deutsch";
Signavio.I18N.fr = "Français";
Signavio.I18N.ru = "Русский";
Signavio.I18N.es = "Español";
Signavio.I18N.nl = "Nederlands";

Signavio.I18N.askForRefresh = "The content of the current folder might have changed.<br/>Do you want to refresh the view?";


Signavio.I18N.visa = "Visa";
Signavio.I18N.mastercard = "Master Card";

// DEFINE THE DEFAULT VALUES FOR THE EXT MSG-BOX
Ext.MessageBox.buttonText.yes = "Yes";
Ext.MessageBox.buttonText.no = "No";
Ext.MessageBox.buttonText.cancel = "Cancel";
Ext.MessageBox.buttonText.close = "Close";
Ext.MessageBox.buttonText.ok = "OK";

// REPOSITORY
if(!Signavio.I18N.Repository) Signavio.I18N.Repository = {};
Signavio.I18N.Repository.leftPanelTitle = "";
Signavio.I18N.Repository.rightPanelTitle = "";


// DATE
if(!Signavio.I18N.Repository.Date) Signavio.I18N.Repository.Date = {};
Signavio.I18N.Repository.Date.ago = "ago";
// SINGULAR
Signavio.I18N.Repository.Date.year = "year";
Signavio.I18N.Repository.Date.month = "month";
Signavio.I18N.Repository.Date.day = "day";
Signavio.I18N.Repository.Date.hour = "hour";
Signavio.I18N.Repository.Date.minute = "minute";
Signavio.I18N.Repository.Date.second = "second";
// PLURAL
Signavio.I18N.Repository.Date.years = "years";
Signavio.I18N.Repository.Date.months = "months";
Signavio.I18N.Repository.Date.days = "days";
Signavio.I18N.Repository.Date.hours = "hours";
Signavio.I18N.Repository.Date.minutes = "minutes";
Signavio.I18N.Repository.Date.seconds = "seconds";
Signavio.I18N.Repository.Date.rest = "seconds";

Signavio.I18N.Repository.Date.defaultDateFormat = "m/d/Y";

Signavio.I18N.Repository.Date.yearss = Signavio.I18N.Repository.Date.years;
Signavio.I18N.Repository.Date.monthss = Signavio.I18N.Repository.Date.months;
Signavio.I18N.Repository.Date.dayss = Signavio.I18N.Repository.Date.days;


// LOGGING TOOL
if(!Signavio.I18N.Repository.Log) Signavio.I18N.Repository.Log = {};
Signavio.I18N.Repository.Log.ERROR = "ERROR";
Signavio.I18N.Repository.Log.CRITICAL = "CRITICAL";
Signavio.I18N.Repository.Log.FATAL = "FATAL";
Signavio.I18N.Repository.Log.LOG = "Log";

Signavio.I18N.Repository.loadingFailed = "The application could not be loaded. Please double check your Internet connection and reload the page.";

Signavio.I18N.Repository.noFeaturesTitle = "No functions";
Signavio.I18N.Repository.noFeaturesMsg = "There are no functions available at the moment.<br/>Please contact the <a href='mailto:[supportMailAdresse]'>support</a> or reload the application.";


// HEADER TEMPLATE
if(!Signavio.I18N.Repository.Header) Signavio.I18N.Repository.Header = {};
Signavio.I18N.Repository.Header.openIdSample = "your.openid.net";
Signavio.I18N.Repository.Header.sayHello = "Hi";
Signavio.I18N.Repository.Header.login = "login";
Signavio.I18N.Repository.Header.loginAsModeler = "login for modeling";
Signavio.I18N.Repository.Header.logout = "logout";
Signavio.I18N.Repository.Header.loginTitle = "Login";
Signavio.I18N.Repository.Header.loginError401 = "The user name and/or password is incorrect!";
Signavio.I18N.Repository.Header.loginError404 = "The user is not activated.";
Signavio.I18N.Repository.Header.loginError500 = "Something went wrong.";
Signavio.I18N.Repository.Header.loginError423 = "You have entered an incorrect password too many times and your account has been locked. To enable you contact again please contact the <a href='mailto:[supportMailAdresse]'>Support</a>";

Signavio.I18N.Repository.Header.windowBtnLogin = "Login";
Signavio.I18N.Repository.Header.windowBtnCancel = "Cancel";
Signavio.I18N.Repository.Header.windowBtnResetPassword = "Reset password";
Signavio.I18N.Repository.Header.windowFieldName = "Email";
Signavio.I18N.Repository.Header.windowFieldUserName = "User name";
Signavio.I18N.Repository.Header.windowFieldPassword = "Password";
Signavio.I18N.Repository.Header.windowFieldRemember = "Remember me";
Signavio.I18N.Repository.Header.windowFieldCaptcha = "Security code";
Signavio.I18N.Repository.Header.windowResetPasswordDesc = "Please enter your email address to receive an email with instructions how to reset your password.";
Signavio.I18N.Repository.Header.windowBtnSend = "Send";
Signavio.I18N.Repository.Header.windowResetPasswordHintOk = "An email has been send to reset your password.";
Signavio.I18N.Repository.Header.windowResetPasswordHintFail = "Failed to send the email to reset your password. Please try again or contact the <a href='mailto:[supportMailAdresse]'>support</a>.";

// CONTEXT PLUGIN
if(!Signavio.I18N.Repository.ContextPlugin) Signavio.I18N.Repository.ContextPlugin = {};
Signavio.I18N.Repository.ContextPlugin.selectedElements = "Selected elements";


// FOLDER PLUGIN
if(!Signavio.I18N.Repository.Folder) Signavio.I18N.Repository.Folder = {};
Signavio.I18N.Repository.Folder.folder = "&raquo; Folders";
Signavio.I18N.Repository.Folder.favorits = "&raquo; Favorites";
Signavio.I18N.Repository.Folder.savedSearch = "&raquo; Saved Search";

Signavio.I18N.Repository.Folder["public"] = "Shared documents";
Signavio.I18N.Repository.Folder["private"] = "My documents";
Signavio.I18N.Repository.Folder["published"] = "Public documents";
Signavio.I18N.Repository.Folder.trash = "Trash";

// ACCESS PLUGIN
if(!Signavio.I18N.Repository.Access) Signavio.I18N.Repository.Access = {};
Signavio.I18N.Repository.Access.user = "User";
Signavio.I18N.Repository.Access.none = "No access rights";
// DEFINE INDIVIDUAL RIGHTS
Signavio.I18N.Repository.Access.Rights = {};

Signavio.I18N.Repository.Access.Rights["warehouse.read"] = "Read";
Signavio.I18N.Repository.Access.Rights["warehouse.write"] = "Read, Write";
Signavio.I18N.Repository.Access.Rights["warehouse.delete"] = "Read, Write, Delete";
Signavio.I18N.Repository.Access.Rights["warehouse.share"] = "Read, Write, Delete, Publish";
Signavio.I18N.Repository.Access.Rights["glossary.open"] = "Dictionary open";
Signavio.I18N.Repository.Access.Rights["glossary.read"] = "Dictionary read";
Signavio.I18N.Repository.Access.Rights["glossary.write"] = "Dictionary write";
Signavio.I18N.Repository.Access.Rights["all"] = "All";

Signavio.I18N.Repository.Access.Rights["warehouse.read_short"] = "R";
Signavio.I18N.Repository.Access.Rights["warehouse.write_short"] = "R, W";
Signavio.I18N.Repository.Access.Rights["warehouse.delete_short"] = "R, W, D";
Signavio.I18N.Repository.Access.Rights["warehouse.share_short"] = "R, W, D, P";
Signavio.I18N.Repository.Access.Rights["glossary.open_short"] = "DO";
Signavio.I18N.Repository.Access.Rights["glossary.read_short"] = "DR";
Signavio.I18N.Repository.Access.Rights["glossary.write_short"] = "DW";
Signavio.I18N.Repository.Access.Rights["all_short"] = "A";


// INFO PLUGIN
if(!Signavio.I18N.Repository.Info) Signavio.I18N.Repository.Info = {};
Signavio.I18N.Repository.Info.noTitle = "No title";
Signavio.I18N.Repository.Info.noDescription = "No summary";
Signavio.I18N.Repository.Info.noSelection = "No elements are selected";
Signavio.I18N.Repository.Info.willBeNotified = "In case of changes, you will get an email notification.";
Signavio.I18N.Repository.Info.wontBeNotified = "";
Signavio.I18N.Repository.Info.notifyMe = "Notify me via email when the diagram changes.";
Signavio.I18N.Repository.Info.dontNotifyMe = "Don't notify me.";
Signavio.I18N.Repository.Info.elementSelected = "elements selected";
Signavio.I18N.Repository.Info.openDiagramPreview = "Open diagram preview";
Signavio.I18N.Repository.Info.closeDiagramPreview = "Close diagram preview";

// DEFINE ATTRIBUTES
Signavio.I18N.Repository.Info.Attributes = {};
Signavio.I18N.Repository.Info.Attributes.name = "Name";
Signavio.I18N.Repository.Info.Attributes.description = "Description";
Signavio.I18N.Repository.Info.Attributes.noname = "No name";
Signavio.I18N.Repository.Info.Attributes.nodescription = "No description";
Signavio.I18N.Repository.Info.Attributes.author = "Author";
Signavio.I18N.Repository.Info.Attributes.created = "Created";
Signavio.I18N.Repository.Info.Attributes.updated = "Update";
Signavio.I18N.Repository.Info.Attributes.info = "edited <b>#{time}</b> ago #{delimiter}by <b>#{user}</b>";
Signavio.I18N.Repository.Info.Attributes.infoMulipleOne = "last edited <b>#{time}</b> ago";
Signavio.I18N.Repository.Info.Attributes.infoMulipleTwo= "last edited between <b>#{time}</b> and <b>#{time2}</b>";
Signavio.I18N.Repository.Info.unworkedCommentsCountPart1 = "There are ";
Signavio.I18N.Repository.Info.unworkedCommentsCountPart2 = " new comments for this model";


// REVISION PLUGIN
if(!Signavio.I18N.Repository.Revision) Signavio.I18N.Repository.Revision = {};
Signavio.I18N.Repository.Revision.none = "No revisions";
Signavio.I18N.Repository.Revision.revert = "Restore this revision";
Signavio.I18N.Repository.Revision.revertPrompt = "You are going to restore the revision '{revision}' of diagram '{model}'.<br/>In addition you can add a specific comment.";
Signavio.I18N.Repository.Revision.approve = "Publish this revision";
Signavio.I18N.Repository.Revision.unapprove = "Unpublish this revision";
Signavio.I18N.Repository.Revision.comment = "Please enter a comment here";
Signavio.I18N.Repository.Revision.btnRevert = "Revert";
Signavio.I18N.Repository.Revision.btnCancel = "Cancel";
Signavio.I18N.Repository.Revision.iconTooltip = "This revision can be accessed (read-only) by users that are not logged in.";
Signavio.I18N.Repository.Revision.compare = "Show changes";

Signavio.I18N.Repository.Revision.publishDlgTitle = "Approve revision for publishing";
Signavio.I18N.Repository.Revision.publishDlgDesc = "You are going to grant read-only access to this revision of the diagram for users that are not logged in. This means the diagram will be publicly accessible.<br/>Do you want to continue?";

Signavio.I18N.Repository.Revision.viewIconTooltipHead = "The latest revision can be accessed (read-only) by users that are not logged in.";
Signavio.I18N.Repository.Revision.viewIconTooltipNoHead = "An older revision can be accessed (read-only) by users that are not logged in.";

Signavio.I18N.Repository.Revision.resetView = "Original";

// BREADCRUMP PLUGIN
if(!Signavio.I18N.Repository.BreadCrumb) Signavio.I18N.Repository.BreadCrumb = {};
Signavio.I18N.Repository.BreadCrumb.delimiter = "&raquo; ";
Signavio.I18N.Repository.BreadCrumb.search = "Search: ";
Signavio.I18N.Repository.BreadCrumb.nrOfResults = "found in {nr} objects";
Signavio.I18N.Repository.BreadCrumb.none = "No directory selected";
Signavio.I18N.Repository.BreadCrumb.goBack = "Back";

// VIEW TEMPLATE
if(!Signavio.I18N.Repository.View) Signavio.I18N.Repository.View = {};
Signavio.I18N.Repository.View.foundInNames = "Found in titles";
Signavio.I18N.Repository.View.foundInDescriptions = "Found in descriptions";
Signavio.I18N.Repository.View.foundInLabels = "Found in diagram elements";
Signavio.I18N.Repository.View.foundInComments = "Found in comments";
Signavio.I18N.Repository.View.foundInRevComments = "Found in revision comments";
Signavio.I18N.Repository.View.foundInMetaData = "Found in custom attributes";

// ICONVIEW PLUGIN
if(!Signavio.I18N.Repository.IconView) Signavio.I18N.Repository.IconView = {};
Signavio.I18N.Repository.IconView.none = "No elements";
Signavio.I18N.Repository.IconView.description = "Icon view";
Signavio.I18N.Repository.IconView.toolTipCommentsSingle = "There is one new comment for this model";
Signavio.I18N.Repository.IconView.toolTipCommentsPlural = "There are {commentsCount} new comments for this model";


if(!Signavio.I18N.Repository.TableView) Signavio.I18N.Repository.TableView = {};
Signavio.I18N.Repository.TableView.foundIn = "Hit";
Signavio.I18N.Repository.TableView.name = "Name";
Signavio.I18N.Repository.TableView.parentName = "in Folder";
Signavio.I18N.Repository.TableView.description = "Description";
Signavio.I18N.Repository.TableView.revision = "Revision";
Signavio.I18N.Repository.TableView.lastChanges = "Last change";
Signavio.I18N.Repository.TableView.lastAuthor = "Last author";
Signavio.I18N.Repository.TableView.toolTip = "List view";
Signavio.I18N.Repository.TableView.toolTipCommentsSingle = "There is one new comment for this model";
Signavio.I18N.Repository.TableView.toolTipCommentsPlural = "There are {commentsCount} new comments for this model";

// ALL OFFERS
if(!Signavio.I18N.Repository.Offer) Signavio.I18N.Repository.Offer = {};
// ADMINISTRATION
Signavio.I18N.Repository.Offer.administrationTitle = "Configure...";
Signavio.I18N.Repository.Offer.administrationDescription = "Administrate configurations, users, user groups and more";
// DELETE
Signavio.I18N.Repository.Offer.deleteTitle = "Delete";
Signavio.I18N.Repository.Offer.deleteDescription = "Move the selected elements to the trash";
Signavio.I18N.Repository.Offer.removeTitle = "Remove";
Signavio.I18N.Repository.Offer.removeDescription = "Remove the selected elements. This action is irreversible.";
Signavio.I18N.Repository.Offer.restoreTitle = "Restore";
Signavio.I18N.Repository.Offer.restoreDescription = "Restore the elements from the trash";
Signavio.I18N.Repository.Offer.deleteQuestion = "Are you sure you want to move the diagram(s) to the trash?";
Signavio.I18N.Repository.Offer.removeQuestion = "Do you really want to permanently delete the selecte diagram(s) from the trash? This action is irreversible.";
Signavio.I18N.Repository.Offer.restoreFailed = "Restoring the selected elements is not possible. You do not have write privilege on the original folder.";
Signavio.I18N.Repository.Offer.restoreFailedTitle = "Restore failed";

// MOVE
Signavio.I18N.Repository.Offer.copyPrefix = " (Copy)";
Signavio.I18N.Repository.Offer.moveTitle = "Move";
Signavio.I18N.Repository.Offer.moveDescription = "Move the selected elements to the specified folder";
Signavio.I18N.Repository.Offer.moveCreateCopy = "Create a copy";
Signavio.I18N.Repository.Offer.moveCreateCopyNot = "Only available if all elements are diagrams.";
Signavio.I18N.Repository.Offer.moveWindowHeader = "Move '#{title}' to:";
Signavio.I18N.Repository.Offer.moveWindowHeaderMultiple = "Move all #{count} selected elements to:";
Signavio.I18N.Repository.Offer.moveBtnOk = "Move";
Signavio.I18N.Repository.Offer.moveBtnCancel = "Cancel";
Signavio.I18N.Repository.Offer.moveAlertTitle = "Move";
Signavio.I18N.Repository.Offer.moveAlertDesc = "You don't have write access for the selected target folder.";


Signavio.I18N.Repository.Offer.copyTitle = "Copy";
Signavio.I18N.Repository.Offer.copyTitleDublicate = "Duplicate";
Signavio.I18N.Repository.Offer.copyDescription = "Copies the selected elements to a specified folder";
Signavio.I18N.Repository.Offer.copyWindowHeader = "Copy '#{title}' to:";
Signavio.I18N.Repository.Offer.copyWindowHeaderMultiple = "Copy all #{count} selected diagrams to:";
Signavio.I18N.Repository.Offer.copyBtnOk = "Copy";
Signavio.I18N.Repository.Offer.copyAlertTitle = "Copy";
Signavio.I18N.Repository.Offer.copyAlertDesc = "You don't have write access for the selected target folder.";
Signavio.I18N.Repository.Offer.copyLinkedError = "The model '{model}' is corrupted.";
Signavio.I18N.Repository.Offer.copyError = "An error occurred while copying. ";

Signavio.I18N.Repository.Offer.includeLinked =  "Copy linked models, too";
Signavio.I18N.Repository.Offer.includeLinkedToolTip =  "Attention, BPMN call activities are not copied.";

Signavio.I18N.Repository.Offer.compareTitle = "Compare revisions/diagrams";

// NEW
Signavio.I18N.Repository.Offer.newTitle = "New";
Signavio.I18N.Repository.Offer.newFolderTitle = "Folder";
Signavio.I18N.Repository.Offer.newFolderDescription = "Create a new Folder";
Signavio.I18N.Repository.Offer.newFolderDefaultTitle = "New Folder";
Signavio.I18N.Repository.Offer.newFFOnly = "It is not possible to use the process editor with your web browser. More information about browser compatibility is available <a target='_blank' href='http://www.signavio.com/en/browser-compatibility.html' >here</a>.";
Signavio.I18N.Repository.Offer.ie9Compatibility = "You are using the <a target='_blank' href='http://windows.microsoft.com/en-GB/internet-explorer/products/ie-9/features/compatibility-view'>compatibility view</a> of the Internet Explorer 9. When using the compatibility view, modelling diagrams is not supported. Please deactivate the compatibility view and try again to edit a diagram.";

Signavio.I18N.Repository.Offer.descriptionIE6TitleNew = "New QuickModel";
Signavio.I18N.Repository.Offer.descriptionIE6TitleEdit = "Edit QuickModel";
Signavio.I18N.Repository.Offer.descriptionIE6Info = "It is not possible to use the process editor with your web browser. More information about browser compatibility is available <a target='_blank' href='http://www.signavio.com/en/browser-compatibility.html' >here</a>.";

Signavio.I18N.Repository.Offer.newWindowFolderTitle = "Create new folder";
Signavio.I18N.Repository.Offer.newWindowFolderDesc = "Please enter a name for the new folder:";

Signavio.I18N.Repository.Offer.spreadsheetNew = "QuickModel";
Signavio.I18N.Repository.Offer.spreadsheetNewDescription = "Create a new QuickModel";

//RENAME
Signavio.I18N.Repository.Offer.renameTitle = "Rename";
Signavio.I18N.Repository.Offer.renameDesc = "Edit name and description of the selected diagram";
Signavio.I18N.Repository.Offer.renameLabelName = "Name";
Signavio.I18N.Repository.Offer.renameLabelDesc = "Description";
Signavio.I18N.Repository.Offer.openSimulation = "Simulate diagram";
Signavio.I18N.Repository.Offer.openSimulationDescription = "Opens the BPMN 2.0 process diagram simulator.";

// Edit
Signavio.I18N.Repository.Offer.editGroupTitle = "Edit";
Signavio.I18N.Repository.Offer.edit = "Edit diagram";
Signavio.I18N.Repository.Offer.editDescription = "Edit the current diagram";
Signavio.I18N.Repository.Offer.spreadsheetEdit = "Edit QuickModel";
Signavio.I18N.Repository.Offer.spreadsheetEditDescription = "Edit the current diagram as QuickModel";

Signavio.I18N.Repository.Offer.openPublisherTitle = "Process portal preview";
Signavio.I18N.Repository.Offer.openPublisherTitleEdit = "Show diagram";
Signavio.I18N.Repository.Offer.doPublishTitle = "Publish in process portal";
Signavio.I18N.Repository.Offer.doPublishDesc = "Provides the ability to publish multiple diagrams to the Process portal at once";
Signavio.I18N.Repository.Offer.openPublisherDesc = "Open the diagram in the process portal";

// SEARCH
Signavio.I18N.Repository.Offer.search = "Search";
Signavio.I18N.Repository.Offer.searchHintExplorer = "Searches through all diagrams the modeler can access. Does not search through dictionary entries and content of linked (or uploaded) documents.";
Signavio.I18N.Repository.Offer.searchHintPortal = "Searches through all published diagrams. Does not search through dictionary entries and content of linked (or uploaded) documents.";
Signavio.I18N.Repository.Offer.searchHintGlossary = "Searches through all dictionary entries. Does not search through content of linked documents.";
// UPDATE
Signavio.I18N.Repository.Offer.updateTitle = "Refresh";
Signavio.I18N.Repository.Offer.updateDescription = "Updates the whole repository";

// ACCESS
Signavio.I18N.Repository.Offer.accessTitle = "Access";
Signavio.I18N.Repository.Offer.accessDescription = "Change access rights for a diagram or folder";

Signavio.I18N.Repository.Offer.help = "Help";
Signavio.I18N.Repository.Offer.helpDesc = "Help";
Signavio.I18N.Repository.Offer.userGuide = "User guide";
Signavio.I18N.Repository.Offer.userGuidePDF = "User guide (PDF)";
Signavio.I18N.Repository.Offer.userGettingStartet = "Getting started";
Signavio.I18N.Repository.Offer.userGettingStartetDesc = "Access screencasts that explain the most important features of the Signavio Process Editor.";
Signavio.I18N.Repository.Offer.userGuideTitle = "User guide - Getting started";
Signavio.I18N.Repository.Offer.userGuideDesc = "Open user guide for the Signavio Process Editor";
Signavio.I18N.Repository.Offer.userGuideDescPDF = "Open user guide as a PDF document for the Signavio Process Editor";
Signavio.I18N.Repository.Offer.openUGNewWindow = "Open user guide in new window.";
Signavio.I18N.Repository.Offer.conventionsOpenInNewWindow = "Open conventions overview in new window.";
Signavio.I18N.Repository.Offer.feedbackName = "Signavio support";
Signavio.I18N.Repository.Offer.feedbackNameEnterprise = "Support";
Signavio.I18N.Repository.Offer.feedbackDesc = "Send a message to the support.";

// MIGRATION
Signavio.I18N.Repository.Offer.migration = "Migrate to BPMN 2.0";
Signavio.I18N.Repository.Offer.migrationDesc = "Migrate BPMN 1.2 diagrams to BPMN 2.0.";
Signavio.I18N.Repository.Offer.migrationFailed = "Migration went wrong!";

// Jump to Parent folder
Signavio.I18N.Repository.Offer.jumpToParentFolderTitle = "Go to parent";
Signavio.I18N.Repository.Offer.jumpToParentFolderDescription = "Jump to folder that contains the diagram.";


if(!Signavio.I18N.Repository.SecurityCenter) Signavio.I18N.Repository.SecurityCenter = {};
if(!Signavio.I18N.Repository.SecurityCenter.Offer) Signavio.I18N.Repository.SecurityCenter.Offer = {};
if(!Signavio.I18N.Repository.SecurityCenter.Span) Signavio.I18N.Repository.SecurityCenter.Span = {};
if(!Signavio.I18N.Repository.SecurityCenter.Button) Signavio.I18N.Repository.SecurityCenter.Button = {};
if(!Signavio.I18N.Repository.SecurityCenter.Hint) Signavio.I18N.Repository.SecurityCenter.Hint = {};
if(!Signavio.I18N.Repository.SecurityCenter.Title) Signavio.I18N.Repository.SecurityCenter.Title = {};

//Invite to discuss
Signavio.I18N.Repository.SecurityCenter.Offer.discussManyTitle = "Invite to comment";
Signavio.I18N.Repository.SecurityCenter.Button.changeModelSelection = "Choose diagrams";
Signavio.I18N.Repository.SecurityCenter.Title.manyModelSelection = "Change selection";
Signavio.I18N.Repository.SecurityCenter.multiSelectDiscussion = "Select the diagrams your colleagues will comment on";
Signavio.I18N.Repository.SecurityCenter.DiagramDiscussionNumber = "Invite to comment on {number} diagrams.";
Signavio.I18N.Repository.SecurityCenter.DiagramDiscussion = "Invite to comment on selected diagrams.";
Signavio.I18N.Repository.SecurityCenter.SendInvitations = "The invitations have been sent successfully.";
Signavio.I18N.Repository.SecurityCenter.SendInvitationsError = "Following invitations could not been sent successfully:";

Signavio.I18N.Repository.SecurityCenter.Offer.shareTitle = "Share";
Signavio.I18N.Repository.SecurityCenter.Offer.shareDesc = "Share a diagram for read-only, commenting or editing access";
Signavio.I18N.Repository.SecurityCenter.Offer.readTitle = "Embed diagram";
Signavio.I18N.Repository.SecurityCenter.Offer.readDesc = "";
Signavio.I18N.Repository.SecurityCenter.Offer.discussTitle = "Invite to comment on the diagram";
Signavio.I18N.Repository.SecurityCenter.Offer.discussDesc = "";
Signavio.I18N.Repository.SecurityCenter.Offer.writeTitle = "Invite to edit the diagram";
Signavio.I18N.Repository.SecurityCenter.Offer.writeError = "During the sending an error is occurred. Please try again.";
Signavio.I18N.Repository.SecurityCenter.Offer.writeDesc = "";

Signavio.I18N.Repository.SecurityCenter.Offer.writeSpreadsheetTitle = "Invite to edit the QuickModel";
Signavio.I18N.Repository.SecurityCenter.Offer.writeSpreadsheetDesc = "";
Signavio.I18N.Repository.SecurityCenter.Offer.manageTitle = "Manage invitations";
Signavio.I18N.Repository.SecurityCenter.Offer.manageDesc = "";
Signavio.I18N.Repository.SecurityCenter.noUser = "No User";

Signavio.I18N.Repository.SecurityCenter.Button.ok = "Send";
Signavio.I18N.Repository.SecurityCenter.Button.cancel = "Cancel";
Signavio.I18N.Repository.SecurityCenter.Button.close = "Close";

// read-only
Signavio.I18N.Repository.SecurityCenter.sendInvitation = "Send invitation";
Signavio.I18N.Repository.SecurityCenter.mailAddressEmptyText = "Please enter a list of email addresses (separated by commas).";
Signavio.I18N.Repository.SecurityCenter.mailTextEmptyText = "Here you can specify the message.";
Signavio.I18N.Repository.SecurityCenter.mailInfoShort = "Send invitation email";
Signavio.I18N.Repository.SecurityCenter.mailInfo = "Send the read access link via email to your colleagues. They will be able to view the diagram without logging in.";
Signavio.I18N.Repository.SecurityCenter.mailTitleText = "Email addresses:";
Signavio.I18N.Repository.SecurityCenter.userTitleText = "User:";
Signavio.I18N.Repository.SecurityCenter.textTitleText = "Message:";
Signavio.I18N.Repository.SecurityCenter.addSender = "Send a copy to me";
Signavio.I18N.Repository.SecurityCenter.sent = "Send!";
Signavio.I18N.Repository.SecurityCenter.Hint.oneReceiverMustDefined = "At least one receiver has to be defined.";
Signavio.I18N.Repository.SecurityCenter.invalidMail = "One or more email addresses are invalid, please use only valid email addresses.";
Signavio.I18N.Repository.SecurityCenter.Hint.oneModelMustDefined = "At least one diagram has to be selected.";
Signavio.I18N.Repository.SecurityCenter.justGetLink = "Just get the Link";
Signavio.I18N.Repository.SecurityCenter.linkTextInformation = "Link:";
Signavio.I18N.Repository.SecurityCenter.linkInputText = "No link created yet. Click 'Generate'";
Signavio.I18N.Repository.SecurityCenter.Button.generate = "Generate";
Signavio.I18N.Repository.SecurityCenter.Button.reset = "Reset";
Signavio.I18N.Repository.SecurityCenter.Button.remove = "Remove";
Signavio.I18N.Repository.SecurityCenter.Hint.pleaseSelectModel = "Please select a diagram.";
Signavio.I18N.Repository.SecurityCenter.mailDefaultReadText = "Hi,\n\nI would like to invite you to view a diagram in the Signavio Process Editor.\n\nBest regards\n";
//embed
Signavio.I18N.Repository.SecurityCenter.infoTextShort = "Click on 'Share read access' and use the displayed HTML code for your wiki or blog. Follow the link 'Preview' to view the result. Attention: At the same time you grant read access to the diagram '{name}' beyond your Signavio workspace.";
Signavio.I18N.Repository.SecurityCenter.infoTextEnterpriseHint = "Access is still restricted to your Intranet.";
Signavio.I18N.Repository.SecurityCenter.shareReadLink = "Share diagram for read-only access";
Signavio.I18N.Repository.SecurityCenter.undoShareReadLink = "Stop sharing the diagram for read-only access";
Signavio.I18N.Repository.SecurityCenter.embedding = "Embedding";
Signavio.I18N.Repository.SecurityCenter.embedHelp = "Use the displayed HTML code for your wiki or blog. On the Signavio website you can find a <a href=\"/help/en/index.html?activate_the_embedding_functio.htm\" target=\"_blank\">list of supported blog and wiki systems.</a>";
Signavio.I18N.Repository.SecurityCenter.embedHtml = "HTML code (copy and embed)";
Signavio.I18N.Repository.SecurityCenter.embedSize = "Size";
Signavio.I18N.Repository.SecurityCenter.embedPreview = "Preview";
Signavio.I18N.Repository.SecurityCenter.embedPreviewDefaultText = "This is an exmaple website demonstrating the embedding of the diagram.";
Signavio.I18N.Repository.SecurityCenter.embedPreviewDescription = "Use the generated HTML code to embed the diagram in your wiki or blog:";
Signavio.I18N.Repository.SecurityCenter.embedWidth = "Width (pixel)";
Signavio.I18N.Repository.SecurityCenter.embedHeight = "Height (pixel)";
Signavio.I18N.Repository.SecurityCenter.embedWidthInfo = "Specify a fixed width for the diagram embedding. If set to 'auto', the width of the embedding will fit to the available space automatically.";
Signavio.I18N.Repository.SecurityCenter.embedHeightInfo = "Specify a fixed height for the diagram embedding. If set to 'auto', the height of the embedding will fit to the available space automatically.";
Signavio.I18N.Repository.SecurityCenter.embedSignup = "Sign up for your own <a href=\"http://www.signavio.com/try\">Signavio account</a>";
Signavio.I18N.Repository.SecurityCenter.image = "Simple image";
Signavio.I18N.Repository.SecurityCenter.imageHelp = "Use the displayed link location to reference a static image representation of the diagram.";
Signavio.I18N.Repository.SecurityCenter.imageLink = "Link to the PNG image";
Signavio.I18N.Repository.SecurityCenter.more = "Google";
Signavio.I18N.Repository.SecurityCenter.moreHelp = "Use the displayed gadget address to embed the diagram as a Google Gadget in iGoogle, Google Wave, Google Sites, Google Docs or other Google services.";
Signavio.I18N.Repository.SecurityCenter.gadgetLink = "URL of the Google Gadget";
Signavio.I18N.Repository.SecurityCenter.embedNotGeneratedText = "This diagram has not been marked for sharing yet. Please click on 'Share diagram for read-only access' in order to get the HTML code to embed the diagram.";
Signavio.I18N.Repository.SecurityCenter.embedSignavioKeywords = "BPM, BPMN 2.0, BPMN tool, cloud, SaaS, EPC, modeling, BPMS, collaboration, repository, web-based, jBPM, BPMN editor, BPMN modeler, BPMN notation, Business Process Modeling Notation, Business Process Model and Notation";
Signavio.I18N.Repository.SecurityCenter.selectView = "Select a view";
Signavio.I18N.Repository.SecurityCenter.originalView = "Original";
Signavio.I18N.Repository.SecurityCenter.mediaWiki = "MediaWiki";
Signavio.I18N.Repository.SecurityCenter.mediaWikiHelp = 'You can embed your diagram in the MediaWiki installation. You should need the extension "SignavioEmbed", which are available under <a href="http://www.mediawiki.org/wiki/Extension:SignavioEmbed" target="_blank" rel="external">http://www.mediawiki.org/wiki/Extension:SignavioEmbed</a>. After these installation you can copy the following code and embed in your article in the MediaWiki.';
Signavio.I18N.Repository.SecurityCenter.mediaWikiLink = "HTML code for MediaWiki (copy and embed)";
Signavio.I18N.Repository.SecurityCenter.mediaWikiLinkNon = "This feature is only for the orginial view of a diagram and does not support views.";


//discussion
Signavio.I18N.Repository.SecurityCenter.sendDiscussion = "Invite your colleagues to comment on the diagram '{name}'.";
Signavio.I18N.Repository.SecurityCenter.sendDiscussionInfo = "Your colleagues will receive an invitation email that directly leads them to the commenting view for the diagram - even without logging in. That way you can also get colleagues involved who are not registered for the workspace.";
Signavio.I18N.Repository.SecurityCenter.sendDiscussionManage = "In order to manage previous invitations please go to the <a href='#' class='x-manage-access'>Manage access rights</a> dialog.";
Signavio.I18N.Repository.SecurityCenter.mailDefaultDiscussText = "Hi,\n\nI would like to invite you to comment on a diagram in the Signavio Process Editor. I am looking forward to your input.\n\nBest regards\n";
Signavio.I18N.Repository.SecurityCenter.mailDefaultDiscussTextForMultiSelection = "Hi,\n\nI would like to invite you to comment on some diagrams in the Signavio Process Editor. I am looking forward to your input.\n\nBest regards\n";
//write
Signavio.I18N.Repository.SecurityCenter.writeInfo = "Invite your colleagues to edit the diagram '{name}'";
Signavio.I18N.Repository.SecurityCenter.writeInfoMultiple = "Invite your colleagues to edit the {count} diagrams";
Signavio.I18N.Repository.SecurityCenter.writeInfoDesc = "This dialog only offers you to notify your colleagues (who must be registered in this workspace). You cannot grant write access to users in this dialog. Write access can only be granted by the workspace administrators.";
Signavio.I18N.Repository.SecurityCenter.writeInfoManage = "Please use the <a href='#' class='x-manage-access'>Manage invitations to comment</a> dialog to review all invitations for this diagram.";
Signavio.I18N.Repository.SecurityCenter.writeInfoManageWithoutLink = "Please use the 'Manage access rights' dialog to review all access rights granted for this diagram.";
Signavio.I18N.Repository.SecurityCenter.Hint.addUser = "Add more users to the workspace using the <a href='#' class='x-add-user'>Invite new users</a> dialog.";
Signavio.I18N.Repository.SecurityCenter.Hint.addUserNoAdmin = "Add more users to the workspace using the 'Invite new users' dialog.";
Signavio.I18N.Repository.SecurityCenter.mailDefaultWriteText = "Hi,\n\nI would like to invite you to edit a diagram in the Signavio Process Editor.\n\Best regards\n";
Signavio.I18N.Repository.SecurityCenter.callbackMsg = "Please select at least one diagram to successfully invite people to comment on your diagram(s).";
Signavio.I18N.Repository.SecurityCenter.Button.selectAll = "<a href='#' class='x-link-select-all'>Select all</a>";
Signavio.I18N.Repository.SecurityCenter.Button.deselectAll = "<a href='#' class='x-link-deselect-all'>Deselect all</a>";
Signavio.I18N.Repository.SecurityCenter.Span.selectionCount = "<span class='y-write-access-count'></span>";
Signavio.I18N.Repository.SecurityCenter.select = " user selected";
//write spreadsheet
Signavio.I18N.Repository.SecurityCenter.writeSpreadsheetInfo = "Invite your colleagues to edit the QuickModel '{name}'";
Signavio.I18N.Repository.SecurityCenter.writeSpreadsheetInfoMultiple = "Invite your colleagues to edit the {count} QuickModel";
Signavio.I18N.Repository.SecurityCenter.mailSpreadsheetDefaultWriteText = "Hi,\n\nI would like to invite you to edit the QuickModel in the Signavio Process Editor.\n\Best regards\n";
//manage
Signavio.I18N.Repository.SecurityCenter.manageInfo = "The following access rights have been granted for the diagram '{name}':";
Signavio.I18N.Repository.SecurityCenter.manageInfoDesc = "The access links that are linked below are valid for an unlimited amount of time. If you want to withdraw an access right you can remove a link in this dialog.";
Signavio.I18N.Repository.SecurityCenter.manageRemove = "Remove";
Signavio.I18N.Repository.SecurityCenter.manageEmpty = "No further access rights";
Signavio.I18N.Repository.SecurityCenter.Hint.json = "Data link";
Signavio.I18N.Repository.SecurityCenter.Hint.png = "PNG read access link";
Signavio.I18N.Repository.SecurityCenter.Hint.talkabout = "Discussion URL";
Signavio.I18N.Repository.SecurityCenter.sortAscendingTT = "Sort users ascending";
Signavio.I18N.Repository.SecurityCenter.sortDescendingTT = "Sort users descending";
Signavio.I18N.Repository.SecurityCenter.filterUser = "Search user";

//NPB
if(!Signavio.I18N.Repository.SecurityCenter.NPBExchange) Signavio.I18N.Repository.SecurityCenter.NPBExchange = {};
Signavio.I18N.Repository.SecurityCenter.NPBExchange.dialogTitle = "Publish diagram in the National Process Library";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.dialogDesc = "Publish your diagram in the National Process Library.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.tabUploadTitle = "Publish";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.tabDownloadTitle = "Import";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.tabUpdateTitle = "Update";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.tabOAuthTitle = "Authorise";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.OAuthDesc = "You can authorise your Signavio account to access your account of the National Process Library. Thereby, you can import diagrams from your NPL account into your Signavio workspace and publish diagrams from your Signavio workspace in the NPL.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.OAuthTokenExists = "Your Signavio account is already authorised to access your NPL account.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.OAuthTokenExistsNot = "Your Signavio account is not yet authorised to access your NPL account.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.OAuthCreateToken = "Grant authorisation";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.OAuthDeleteToken = "Withdraw authorisation";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.OAuthCreateTokenDialogTitle = "Grant authorisation";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.OAuthCreateTokenDialogDesc = "Click on the link below to authorise your Signavio account to access your National Process Library account. You will be forwarded to the National Process Library to confirm the access.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.OAuthCreateTokenDialogLink = "Grant authorisation";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.OAuthDeleteTokenDialogTitle = "Withdraw authorisation";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.OAuthDeleteTokenDialogDesc = "Do you really want to withdraw the authorisation to access your NPL account?";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.uploadBtn = "Publish";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.uploadDesc = "Publish the selected diagram in the National Process Library.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.noVersionExists = "There is no version of the diagram in the NPL, yet.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.oldVersionExists = "There is already another (older) version of this diagram in the NPL.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.oldVersionExistsCreate = "create new process in the NPL";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.oldVersionExistsOverwrite = "overwrite existing process in the NPL";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.addMissingAttributes = "Some mandatory attributes have to be added to the diagram before it can be published. Please open the Editor and add the missing NPL attibutes: <a href=\"{url}\" target=\"blank\">Open Editor</a>";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.uploadSuccessDialogTitle = "Publishing successful";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.uploadSuccessDialogDesc = "The diagram was published successfully in the National Process Library. The following link will lead you to the respective procss in the NPL:";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.uploadDialogSuccessDialogLink = "Open process in the NPL";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.downloadBtn = "Download";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.downloadDesc = "Enter in the following text field a link or the ID of a process stored in the National Process Library that shall be imported to your Signavio workspace.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.downloadLabel = "Link or ID";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.updateBtn = "Update";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.updateDesc = "The selected diagram is already connected with a process in the National Process Library. Here you can check if there is a newer version available.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.updateProcessLink = "Open the process in the NPL";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.checkForUpdateLink = "Check for updates";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.waitTitle = "Please wait";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.waitUpload = "The diagram is being published.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.waitDownload = "The diagram is being imported.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.waitUpdateCheck = "The up-to-dateness of the diagram is being checked.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.waitUpdate = "The diagram is being updated.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.clearanceLevel = "Clearance";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.options = "Options";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.userGuideTitle = "NPL User Guide";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.userGuideDesc = "User Guide for the National Process Library";
if(!Signavio.I18N.Repository.SecurityCenter.NPBExchange.UpdateStatus) Signavio.I18N.Repository.SecurityCenter.NPBExchange.UpdateStatus = {};
Signavio.I18N.Repository.SecurityCenter.NPBExchange.UpdateStatus.updateAvailable = "A new version is available. If you click on update the new version from the NPL will be saved as a new revision for the diagram in your workspace.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.UpdateStatus.noUpdate = "No update is available.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.UpdateStatus.noModel = "There is no process in the NPL for the selected diagram.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.UpdateStatus.unsupportedModel = "A new version of the process is available in the NPL. Sadly, it contains no diagram that is compatible with Signavio.";
if (!Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors)  Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors = {};
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.generalErrorTitle = "Error";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.generalErrorDesc = "An error occured and the functionality is currently not available.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.uploadErrorTitle = "Publishing failed";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.lacksReadRights = "You do not have the necessary read rights to import the diagram. Please ask the responsible person to grant you the necessary rights for the process.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.lacksWriteRights = "You do not have the necessary write rights. Please ask the responsible person to grant you the necessary rights for the process or publish the diagram as a new process in the NPL.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.newRevision = "A new version of the diagram is available in the NPL. You can either update your diagram or publish it as a new process in the NPL.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.uploadUnknownError = "An error occured and the diagram was not published.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.uploadDoesNotExist = "The publishing failed because the process, that shall be overwritten, does not exist anymore.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.downloadErrorTitle = "Import failed";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.downloadUnknownError = "An error occured and the diagram was not imported.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.missingModelId = "The input did not contain a valid ID for a process in the NPL.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.createNewToken = "There seems to be a problem with the authorized access to your NPL account. If the problem occurs again, please withdraw and grant the authorisation again.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.onlyImage = "No compatible diagram could be found. Instead the embedded image was imported.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.onlyImageTitle = "Image imported";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.noContent = "No compatible diagram or image could be imported.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.updateLacksReadRights = "You do not have the necessary read rights for the NPL process to update the diagram. Please ask the responsible person to grant you the necessary rights for the process.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.updateDoesNotExist = "The update failed because the process does not exist anymore.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.updateErrorTitle = "Update failed";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.updateUnknownError = "An error occured and the diagram was not updated.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.updateUnsupportedDiagram = "The update did not contain a Signavio compatible diagram. Therefore the diagram was not updated.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.fileTooBig = "The file, which is provided by the National Process Library, is too big and cannot be imported.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.fileQuotaExceeded = "You do not have enough free memory space to save the image.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.couldNotGenerateSvg = "No graphical representation could be generated for the diagram.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.unsupportedImageFormat = "The image format is not supported.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.unsupportedStencilset = "The diagram type is not supported.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.oauthErrorTitle = "Granting the authorisation not possible";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.oauthErrorDesc = "Currently, you cannot grant the authorisation for Signavio to access your NPL account. Please, try it again later.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.tooManyDiagrams = "Nothing could be imported, because the file from NPL contains multiple diagrams.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.noNPBExtension = "The selected diagram does not contain the mandatory NPL attributes. Please, open the diagram in the Editor and add the missing attributes.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.exportErrorTitle = "Export not possible";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.exportErrorDesc = "The selected diagram does not contain any of the mandatory NPL attributes. Please, open the diagram in the editor and add the missing attributes.";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.Errors.leikaNotAccepted = "The LeiKa key you selected is not recognised by the National Process Library. Please, select another LeiKa key.";
if (!Signavio.I18N.Repository.SecurityCenter.NPBExchange.ClearanceLevel)  Signavio.I18N.Repository.SecurityCenter.NPBExchange.ClearanceLevel = {};
Signavio.I18N.Repository.SecurityCenter.NPBExchange.ClearanceLevel.OE = "Public";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.ClearanceLevel.PR = "Private";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.ClearanceLevel.SP = "Custom";
Signavio.I18N.Repository.SecurityCenter.NPBExchange.ClearanceLevel.OG = "Open Government Data";

//configuration of my profile
Signavio.I18N.Repository.Offer.myProfileTitle = "My profile";
Signavio.I18N.Repository.Offer.myProfileDesc = "Configure your user name or password";
Signavio.I18N.Repository.Offer.certificateTitle = "Manage portal certificate";
Signavio.I18N.Repository.Offer.certificateDescription = "To publish certain process diagrams, you can manage a certificate for it here.";

Signavio.I18N.Repository.Offer.certificateLabel= "To have read access to all published process diagrams, you will need a client certificate. Here you can create, download or manage such certificates. Those certificates can be rolled out to all users, which then have read access to all published diagrams without any login or user credentials. For further information please read the <a href='https://editor.signavio.com/help/de/installation_eines_zertifikats.htm' target='_blank'>manual</a>.";
Signavio.I18N.Repository.Offer.certificateCreate = "Create certificate";
Signavio.I18N.Repository.Offer.certificateDownload = "Download certificate";
Signavio.I18N.Repository.Offer.certificateDeactivate = "deactivate";
Signavio.I18N.Repository.Offer.certificateActivate = "activate";
Signavio.I18N.Repository.Offer.certificateRemove = "remove";
Signavio.I18N.Repository.Offer.certificatePassword = "Password:";
Signavio.I18N.Repository.Offer.certificateDeleteTitle = "Remove certificate";
Signavio.I18N.Repository.Offer.certificateDeleteDescription = "Do you really want to remove the certificate?<br/>This action cannot be reverted. <br/>The used certificate will be unauthorized.";
Signavio.I18N.Repository.Offer.certificateExpires = "Expires:";
Signavio.I18N.Repository.Offer.certificateDownloadSharePointInfo = "Here you can download a preconfigured Microsoft SharePoint component which you than can install to your intranet.";
Signavio.I18N.Repository.Offer.certificateDownloadSharePoint = "Download SharePoint Webpart";
Signavio.I18N.Repository.Offer.certificateNoQuotaHint = "Currently there is no user quota left to access the BPM Collaboration Portal. Please contact the Signavio Support via E-Mail <a href=\"mailto:[supportMailAdresse]\">[supportMailAdresse]</a> or phone (Tel. [supportPhoneNumber]), to order additional user quota to access the BPM Collaboration Portal.";
Signavio.I18N.Repository.Offer.certificateSharepointVersion = "SharePoint Version";
Signavio.I18N.Repository.Offer.certificateSharepointVersionAuthMode = "Authentication Mode";
Signavio.I18N.Repository.Offer.certificateSharepointCertBased = "Certificate based authentication";
Signavio.I18N.Repository.Offer.certificateSharepointLDAPBased = "LDAP based authentication";
Signavio.I18N.Repository.Offer.certificateSharepointAzureAD = "Windows Azure Active Directory based authentication";

//security center window
Signavio.I18N.Repository.SecurityCenter.title = "Manage users";
Signavio.I18N.Repository.SecurityCenter.btn = "Setup";
Signavio.I18N.Repository.SecurityCenter.btnManage = "Manage users";


//security center window - Tab Manage Access Rights
Signavio.I18N.Repository.SecurityCenter.tabManage = "Access rights";
Signavio.I18N.Repository.SecurityCenter.accessRightPanel = '<div>Access right for \'{[Signavio.I18N.Repository.Folder[values.type]||values.name]}\':</div>';
Signavio.I18N.Repository.SecurityCenter.accessRightPanelEmpty = '<div>Access right:</div>';
Signavio.I18N.Repository.SecurityCenter.accessRightRemoveTitle = "Remove access right";
Signavio.I18N.Repository.SecurityCenter.accessRightRemoveDesc = "Are you sure to remove the access right?";
Signavio.I18N.Repository.SecurityCenter.accessRightRemoveDescWarning = "You are going to remove your right to grant privileges.<br/>If you continue you might not be able to define access rights any more.<br/>Do you want to continue?";
Signavio.I18N.Repository.SecurityCenter.additionalAccessRight = "Additional access rights:";

Signavio.I18N.Repository.SecurityCenter.accessRightColumnUser = "User/Group";
Signavio.I18N.Repository.SecurityCenter.accessRightColumnRight = "Right";
Signavio.I18N.Repository.SecurityCenter.accessRightColumnInherit = "Inherited from";
Signavio.I18N.Repository.SecurityCenter.accessRightColumnDelete = "Remove";
Signavio.I18N.Repository.SecurityCenter.accessRightColumnEntryDelete = "Remove";

Signavio.I18N.Repository.SecurityCenter.accessRightAddLabel = "Add a new access right:";
Signavio.I18N.Repository.SecurityCenter.accessRightAddUser = "User/Group";
Signavio.I18N.Repository.SecurityCenter.accessRightAddRight = "Select a right...";
Signavio.I18N.Repository.SecurityCenter.accessRightAddBtn = "Add";
Signavio.I18N.Repository.SecurityCenter.accessRightAddFailureTitle = "Add access right";
Signavio.I18N.Repository.SecurityCenter.accessRightAddFailureDesc = "Access right could not been added. Maybe it is already defined.";
Signavio.I18N.Repository.SecurityCenter.accessRightAddInvalidInputRight = "Access right could not been added, because there is no access right selected.";
Signavio.I18N.Repository.SecurityCenter.accessRightAddInvalidInputUser = "Access right could not been added, because there is no user/group selected.";
Signavio.I18N.Repository.SecurityCenter.accessRightWaiting = "Changes will be applied...";

Signavio.I18N.Repository.SecurityCenter.tabExternal = "Read access";
Signavio.I18N.Repository.SecurityCenter.tabExternalLabel = '<div>Read access:</div>';
Signavio.I18N.Repository.SecurityCenter.tabExternalLabelFolder = '<div>Read access for \'{[Signavio.I18N.Repository.Folder[values.type]||values.name]}\':</div>';
Signavio.I18N.Repository.SecurityCenter.tabExternalDescription = "Here you can define read access of published diagrams for certain groups or users.";
Signavio.I18N.Repository.SecurityCenter.tabExternalNone = "No read access defined";
Signavio.I18N.Repository.SecurityCenter.tabExternalPage = "Page";

//security center window - Tab Manage Groups
Signavio.I18N.Repository.SecurityCenter.tabGroup = "User groups";
Signavio.I18N.Repository.SecurityCenter.titleGroupTree = "Groups";

Signavio.I18N.Repository.SecurityCenter.groupAddTitle = "Add group";
Signavio.I18N.Repository.SecurityCenter.groupAddDesc = "Please define the name for the group";
Signavio.I18N.Repository.SecurityCenter.groupAddDefaultName = "New group";
Signavio.I18N.Repository.SecurityCenter.groupDeleteTitle = "Remove group";
Signavio.I18N.Repository.SecurityCenter.groupDeleteDesc = "Do you really want to remove the group?";

Signavio.I18N.Repository.SecurityCenter.groupFormName = "Name";
Signavio.I18N.Repository.SecurityCenter.groupFormNameEmpty = "Name";
Signavio.I18N.Repository.SecurityCenter.groupFormParentGroup = "Parent groups";
Signavio.I18N.Repository.SecurityCenter.groupFormParentGroupEmpty = "No parent groups";
Signavio.I18N.Repository.SecurityCenter.groupFormChildGroup = "Users/Groups";
Signavio.I18N.Repository.SecurityCenter.groupFormChildGroupEmpty = "No users/groups";
Signavio.I18N.Repository.SecurityCenter.groupFormAddChildGroup = "Add user/group";
Signavio.I18N.Repository.SecurityCenter.groupFormAddChildGroupEmpty = "User/Group";
Signavio.I18N.Repository.SecurityCenter.groupFormAddWarningTitle = "Add user/group";
Signavio.I18N.Repository.SecurityCenter.groupFormAddWarningDesc = "Please select a child user group or an user.";
Signavio.I18N.Repository.SecurityCenter.groupFormRemoveGroup = "Remove";
Signavio.I18N.Repository.SecurityCenter.groupFormAddBtn = "Add";
Signavio.I18N.Repository.SecurityCenter.groupFormChangeNameBtn = "Change";


//security center window - Tab Manage User
Signavio.I18N.Repository.SecurityCenter.tabUser = "Users";
Signavio.I18N.Repository.SecurityCenter.titleUsersTree = "Users";
Signavio.I18N.Repository.SecurityCenter.removeUserTitle = "Remove user";
Signavio.I18N.Repository.SecurityCenter.removeUserDesc = "Do you really want to permanently delete this user? Please note that all content of the private \"My Documents\" folder are permanently deleted as well.";
Signavio.I18N.Repository.SecurityCenter.userFieldGroup = "Groups";
Signavio.I18N.Repository.SecurityCenter.search = "Search";
Signavio.I18N.Repository.SecurityCenter.userSearchEmptyText = "No Users were found for the given search parameters.";
Signavio.I18N.Repository.SecurityCenter.addGroupToUserTitle = "Add Group";
Signavio.I18N.Repository.SecurityCenter.addGroupComboEmptyText = "Group";
Signavio.I18N.Repository.SecurityCenter.addGroupComboNoGroupsEmptyText = "No Groups available";
Signavio.I18N.Repository.SecurityCenter.addGroupToUserDesc = "Here you can add the user <b>{user}</b> to the user group:";
Signavio.I18N.Repository.SecurityCenter.registerNewUser = "Register new users";

//security center window - Tab Invitation
Signavio.I18N.Repository.SecurityCenter.tabInvitation = "New users";
Signavio.I18N.Repository.SecurityCenter.titleInvitationTree = "Open invitations";
Signavio.I18N.Repository.SecurityCenter.invitationAlertNoLicense = "There is no license selected. Please choose one of the available licenses or purchase a new one.";
Signavio.I18N.Repository.SecurityCenter.invitationAlertUserExists = "A user with the email address already exists in this workspace, please use another one.";
Signavio.I18N.Repository.SecurityCenter.invitationAlertInvitationExists = "An invititation is already send to this email address, please use another email address or remove the previous invitation.";
Signavio.I18N.Repository.SecurityCenter.invitationAlertSuccessUser = "Because the user has already an account in another workspace, it is not neccessary to verify the email address. That is why the user has been added to workspace and can now be found in the user management.";
Signavio.I18N.Repository.SecurityCenter.invitationAlertSuccessInvitation = "An invitation has been sent to the entered email address.";
Signavio.I18N.Repository.SecurityCenter.removeInvitationTitle = "Remove invitation";
Signavio.I18N.Repository.SecurityCenter.removeInvitationDesc = "Do you really want to remove the selected invitation?";


if(!Signavio.I18N.Repository.Feedback) Signavio.I18N.Repository.Feedback = {};
Signavio.I18N.Repository.Feedback.pTitle = "Contact us for any kind of support request or feedback";
Signavio.I18N.Repository.Feedback.pName = "Name";
Signavio.I18N.Repository.Feedback.pEmail = "Email";
Signavio.I18N.Repository.Feedback.pSubject = "Subject";
Signavio.I18N.Repository.Feedback.pMsg = "Description/Message";
Signavio.I18N.Repository.Feedback.pEmpty = "* Please describe your request. Please provide as detailed information as possible.\n* For bug reports, please list the steps how to reproduce the problem and describe the output you expected.";
Signavio.I18N.Repository.Feedback.pAttach = "Attach current diagram";
Signavio.I18N.Repository.Feedback.pAttachDesc = "This information can be helpful for debugging purposes. If your diagram contains any sensitive data, remove it before sending or uncheck this behavior.";
Signavio.I18N.Repository.Feedback.pBrowser = "Information about your browser and environment";
Signavio.I18N.Repository.Feedback.pBrowserDesc = "This information has been auto-detected from your browser. It can be helpful if you encountered a bug associated with browser-specific behavior.";
Signavio.I18N.Repository.Feedback.submit = "Send message";
Signavio.I18N.Repository.Feedback.sending = "Sending message ...";
Signavio.I18N.Repository.Feedback.success = "Success";
Signavio.I18N.Repository.Feedback.successMsg = "Thank you for your feedback!";
Signavio.I18N.Repository.Feedback.failure = "Failure";
Signavio.I18N.Repository.Feedback.failureMsg = "The message could not be sent!";
Signavio.I18N.Repository.Feedback.enterSubject = "Please enter a subject that briefly describes the problem or question.";
Signavio.I18N.Repository.Feedback.enterDescription = "Please describe your problem or question as detailed as possible.";

// ADMINISTRATION
if(!Signavio.I18N.Repository.Administration) Signavio.I18N.Repository.Administration = {};
Signavio.I18N.Repository.Administration.title = "Configure...";
Signavio.I18N.Repository.Administration.store = "Save";
Signavio.I18N.Repository.Administration.cancel = "Cancel";
Signavio.I18N.Repository.Administration.reject = "Reset";
Signavio.I18N.Repository.Administration.panelGeneral = "My Profile";
Signavio.I18N.Repository.Administration.fieldName = "Name";
Signavio.I18N.Repository.Administration.fieldPrincipal = "Email";
Signavio.I18N.Repository.Administration.fieldCompany = "Company/Org.";
Signavio.I18N.Repository.Administration.fieldPassword = "Password";
Signavio.I18N.Repository.Administration.fieldConfirmPassword = "Confirm password";
Signavio.I18N.Repository.Administration.fieldGlobalAccess = "Global access";
Signavio.I18N.Repository.Administration.fieldProfile = "Profile";
Signavio.I18N.Repository.Administration.fieldUsers = "Users";
Signavio.I18N.Repository.Administration.fieldTitle = "Academic title";
Signavio.I18N.Repository.Administration.fieldFirstName = "First name";
Signavio.I18N.Repository.Administration.fieldLastName = "Last name";
Signavio.I18N.Repository.Administration.fieldDescription = "Description";
Signavio.I18N.Repository.Administration.fieldMail = "Email";
Signavio.I18N.Repository.Administration.fieldAccounts = "Accounts";
Signavio.I18N.Repository.Administration.fieldActive = "Enabled";
Signavio.I18N.Repository.Administration.loginAttemptsExceeded = "Login Attempts Exceeded";
Signavio.I18N.Repository.Administration.fieldLanguage = "Language";
Signavio.I18N.Repository.Administration.fieldStreet = "Street";
Signavio.I18N.Repository.Administration.fieldStreet2 = "Street";
Signavio.I18N.Repository.Administration.fieldCity = "City";
Signavio.I18N.Repository.Administration.fieldCountry = "Country";
Signavio.I18N.Repository.Administration.fieldZip = "Zip";
Signavio.I18N.Repository.Administration.fieldPhone = "Phone";
Signavio.I18N.Repository.Administration.fieldFax = "Fax";
Signavio.I18N.Repository.Administration.fieldCreditCard = "Credit card";
Signavio.I18N.Repository.Administration.fieldCardNumber = "Card number";
Signavio.I18N.Repository.Administration.fieldCvcNumber = "CVC";
Signavio.I18N.Repository.Administration.fieldGoodThru = "Good thru";
Signavio.I18N.Repository.Administration.fieldAccountName = "Account owner";
Signavio.I18N.Repository.Administration.fieldAccountNo = "Account number";
Signavio.I18N.Repository.Administration.fieldBankName = "Bank name";
Signavio.I18N.Repository.Administration.fieldBankNumber = "Bank number";
Signavio.I18N.Repository.Administration.fieldLicense = "Edition";
Signavio.I18N.Repository.Administration.fieldInvitationLink = "Invitation link";
Signavio.I18N.Repository.Administration.isAlwaysGrantedAccess = "Always access";
Signavio.I18N.Repository.Administration.isAlwaysGrantedAccessTooltip = "This user will not be considered for monthly concurrent and has always access to the workspace.";
Signavio.I18N.Repository.Administration.isAlwaysGrantedAccessDescription = "This user has always access and will not be considered for the <i>monthly concurrent</i> mechanism.";
Signavio.I18N.Repository.Administration.fieldIsMonthlyConcurrent = "Monthly Concurrent Licensing";

Signavio.I18N.Repository.Administration.panelInvoice = "Invoice";
Signavio.I18N.Repository.Administration.panelDebit = "Debit";
Signavio.I18N.Repository.Administration.panelCredit = "Credit";
Signavio.I18N.Repository.Administration.panelUsers = "User management";
Signavio.I18N.Repository.Administration.panelGroup = "Group management";
Signavio.I18N.Repository.Administration.panelInvite = "Invite new users";
Signavio.I18N.Repository.Administration.activeUsers = "Active users";
Signavio.I18N.Repository.Administration.pendingUsers = "Pending invitations";
Signavio.I18N.Repository.Administration.newGroup = "New Group";
Signavio.I18N.Repository.Administration.add = "Add";
Signavio.I18N.Repository.Administration.groups = "Groups";
Signavio.I18N.Repository.Administration.users = "Users";
Signavio.I18N.Repository.Administration.groupsNo = "No Groups";
Signavio.I18N.Repository.Administration.deleteUserMsg = "The user and all the diagrams in the user's private directory will be deleted. Do you really want to proceed?";

Signavio.I18N.Repository.Administration.btnPasswortReset = "Send email to reset the password.";

Signavio.I18N.Repository.Administration.resetPasswordTitle = "Reset password";
Signavio.I18N.Repository.Administration.resetPasswordDescTooShort = "Password is too short, please use at least 6 characters";
Signavio.I18N.Repository.Administration.resetPasswordDescNotSimilar = "Second password does not fit to the first one.";

Signavio.I18N.Repository.Administration.fieldLicenses = "Edition";

Signavio.I18N.Repository.Administration.quickmodel = 'QuickModel <small>({count} account{[values.count == 1 ?"":"s" ]} left)</small>';
Signavio.I18N.Repository.Administration.team = 'Basic Edition <small>({count} account{[values.count == 1 ?"":"s" ]} left)</small>';
Signavio.I18N.Repository.Administration.premium = 'Professional (1st-Gen) Edition <small>({count} account{[values.count == 1 ?"":"s" ]} left)</small>';
Signavio.I18N.Repository.Administration.professional = 'Professional Edition <small>({count} account{[values.count == 1 ?"":"s" ]} left)</small>';
Signavio.I18N.Repository.Administration.corporate = 'Corporate Edition <small>({count} account{[values.count == 1 ?"":"s" ]} left)</small>';
Signavio.I18N.Repository.Administration.ultimate = 'Ultimate Edition <small>({count} account{[values.count == 1 ?"":"s" ]} left)</small>';
Signavio.I18N.Repository.Administration.archimate = 'ArchiMate Edition <small>({count} account{[values.count == 1 ?"":"s" ]} left)</small>';
Signavio.I18N.Repository.Administration.trial = "Trial Edition";
Signavio.I18N.Repository.Administration.upgrade = "Trial. Upgrade now.";
Signavio.I18N.Repository.Administration.upgradeText = "There are <b>{count}</b> more unused {licenseName} license{[values.count == 1 ?\"\":\"s\" ]} available. <br/>Do you want {user} assign one?";
Signavio.I18N.Repository.Administration.upgradeTextMulti = "There are unused user licenses.<br/>Which type would you like to use to the upgrade the selected user? ";
Signavio.I18N.Repository.Administration.upgradeNone = "None";
Signavio.I18N.Repository.Administration.upgradeDesc = "The management of the licenses can only proceed the tenant owner.";
Signavio.I18N.Repository.Administration.expired = "Expired";

Signavio.I18N.Repository.Administration.deleteItem = 'Do you really want to delete the item(s)?';

Signavio.I18N.Repository.Administration.pleasePurchase = "<div>If you need new user accounts please go to <a href='#' class='x-purchase'>Purchase</a>.</div>";
Signavio.I18N.Repository.Administration.pleasePurchaseNow = "<div>To invite new colleagues please go to <a href='#' class='x-purchase'>Purchase</a> to buy new user accounts.</div>";
Signavio.I18N.Repository.Administration.inviteAcademicDesc = "<div>To invite a new user to this workspace, please add her/his email address.</div>";
Signavio.I18N.Repository.Administration.inviteLinkAcademicDesc = "<div>You can also invite a new user to this workspace by sending this link.</div>";

Signavio.I18N.Repository.Administration.mailToAllTitle = "Email addresses";
Signavio.I18N.Repository.Administration.mailToAllUsers = "This is a list of the email addresses of all users in your workspace. To write an email to all users of your workspace, copy the list, create a new email in your email application, and insert the list as receivers to the email.";
Signavio.I18N.Repository.Administration.mailToAllClose = "Close";

// PURCHASE
if(!Signavio.I18N.Repository.Purchase) Signavio.I18N.Repository.Purchase = {};
Signavio.I18N.Repository.Purchase.purchaseLink = 'Free Trial - {count} day{[values.count == 1 ?"":"s" ]} left. <u>Purchase now</u>.';
Signavio.I18N.Repository.Purchase.linkTitle = "Purchase now!";
Signavio.I18N.Repository.Purchase.cancel = "Cancel";
Signavio.I18N.Repository.Purchase.refresh = "Reload";
Signavio.I18N.Repository.Purchase.logout = "Logout";
Signavio.I18N.Repository.Purchase.title = "Purchase user accounts";
Signavio.I18N.Repository.Purchase.introNone = "On this page you can purchase new user accounts for the Signavio Process Editor.";
Signavio.I18N.Repository.Purchase.introPrefix = "Here you can purchase additional user accounts for the Signavio Process Editor.<br/>You have bought {licenses} already.";
Signavio.I18N.Repository.Purchase.introSubsequence = "for the";
Signavio.I18N.Repository.Purchase.introAccount = "user account";
Signavio.I18N.Repository.Purchase.introAccounts = "user accounts";
Signavio.I18N.Repository.Purchase.introConcat = "and";
Signavio.I18N.Repository.Purchase.introProf = "Here you can purchase additional user accounts for the Signavio Process Editor.<br/>You have bought <b>{countProf}</b> user account{[values.countProf==1?\"\":\"s\"]} for the Professional Edition already.";
Signavio.I18N.Repository.Purchase.introTeam = "Here you can purchase additional user accounts for the Signavio Process Editor.<br/>You have bought <b>{countTeam}</b> user account{[values.countTeam==1?\"\":\"s\"]} for the Basic Edition already.";
Signavio.I18N.Repository.Purchase.introProfAndTeam = "Here you can purchase additional user accounts for the Signavio Process Editor.<br/>You have bought <b>{countProf}</b> user account{[values.countProf==1?\"\":\"s\"]} for the Professional Edition and <b>{countTeam}</b> user account{[values.countTeam==1?\"\":\"s\"]} for the Basic Edition already.";
Signavio.I18N.Repository.Purchase.introTrial = "Until {date} you can still use the tool for free.";
Signavio.I18N.Repository.Purchase.edition = "Edition";
Signavio.I18N.Repository.Purchase.editionHint = "Please choose the edition.";
Signavio.I18N.Repository.Purchase.team = "Basic Edition";
Signavio.I18N.Repository.Purchase.premium = "Professional Edition";
Signavio.I18N.Repository.Purchase.professional = "Professional Edition";
Signavio.I18N.Repository.Purchase.corporate = "Corporate Edition";
Signavio.I18N.Repository.Purchase.ultimate = "Ultimate Edition";
Signavio.I18N.Repository.Purchase.quickmodel = "QuickModel Edition";
Signavio.I18N.Repository.Purchase.archimate = "ArchiMate Edition";
Signavio.I18N.Repository.Purchase.licenseTitleUltimate = 'Ultimate Edition <a href="http://www.signavio.com/en/products/process-editor-as-a-service.html" target="_blank">Details</a>';
Signavio.I18N.Repository.Purchase.licenseTitleCorporate= 'Corporate Edition <a href="http://www.signavio.com/en/products/process-editor-as-a-service.html" target="_blank">Details</a>';
Signavio.I18N.Repository.Purchase.licenseTitleProfessional = 'Professional Edition <a href="http://www.signavio.com/en/products/process-editor-as-a-service.html" target="_blank">Details</a>';
Signavio.I18N.Repository.Purchase.licenseTitlePremium = 'Professional (1st-Gen) Edition <a href="http://www.signavio.com/en/products/process-editor-as-a-service.html" target="_blank">Details</a>';
Signavio.I18N.Repository.Purchase.licenseTitleTeam = 'Basic edition <a href="http://www.signavio.com/en/products/process-editor-as-a-service.html" target="_blank">Details</a>';
Signavio.I18N.Repository.Purchase.license = "User accounts";
Signavio.I18N.Repository.Purchase.licenseHint = "Please choose the users you want to purchase an account for.";
Signavio.I18N.Repository.Purchase.moreLicenses = "additional user accounts";
Signavio.I18N.Repository.Purchase.moreHint = "You can invite more users later on and assign the purchased accounts to them. Also, users who have been invited but have not accepted yet can have a purchased account assigned.";
Signavio.I18N.Repository.Purchase.intervalDesc = "Payment interval";
Signavio.I18N.Repository.Purchase.intervalDescHint = "The payment interval starts on the first day of the quarter (if quarterly interval is selected) or on the first day of the year (if yearly is selected). The first payment interval starts on the day of the purchase and spans at least 15 days. It ends on the next posible quarter or year end.";
Signavio.I18N.Repository.Purchase.annually = "Yearly";
Signavio.I18N.Repository.Purchase.quarterly = "Quarterly";
Signavio.I18N.Repository.Purchase.monthly = "Monthly";
Signavio.I18N.Repository.Purchase.user = "user";
Signavio.I18N.Repository.Purchase.users = "Users";
Signavio.I18N.Repository.Purchase.firstRate = "First payment interval";
Signavio.I18N.Repository.Purchase.rate = "Monthly payment rate";
Signavio.I18N.Repository.Purchase.validating = "Validating...";
Signavio.I18N.Repository.Purchase.currency = "€";
Signavio.I18N.Repository.Purchase.paymentMethod = "Payment method";
Signavio.I18N.Repository.Purchase.billingDesc = 'Order per invoice';
Signavio.I18N.Repository.Purchase.billingDescMore = 'Payable within 14 days';
Signavio.I18N.Repository.Purchase.billingPaypal = 'PayPal';
Signavio.I18N.Repository.Purchase.billingPaypalMore = 'You will receive further information to pay via PayPal';
Signavio.I18N.Repository.Purchase.billingCreditcard = 'Credit card (MasterCard, VISA, American Express)';
Signavio.I18N.Repository.Purchase.billingCreditcardMore = 'You will receive further information to pay via credit card';
Signavio.I18N.Repository.Purchase.billingAddress = "Billing address";
Signavio.I18N.Repository.Purchase.company = "Company";
Signavio.I18N.Repository.Purchase.street = "Street";
Signavio.I18N.Repository.Purchase.street2 = "Street (continued)";
Signavio.I18N.Repository.Purchase.zip = "ZIP";
Signavio.I18N.Repository.Purchase.city = "City";
Signavio.I18N.Repository.Purchase.contactInformation = "Contact information";
Signavio.I18N.Repository.Purchase.name = "Name";
Signavio.I18N.Repository.Purchase.firstName = "First name";
Signavio.I18N.Repository.Purchase.lastName = "Last name";
Signavio.I18N.Repository.Purchase.mail = "Email";
Signavio.I18N.Repository.Purchase.phone = "Phone";
Signavio.I18N.Repository.Purchase.fax = "Fax";
Signavio.I18N.Repository.Purchase.customer = "Billing address";
Signavio.I18N.Repository.Purchase.customerTemplate = '<div>{companyName}</div><div><span>{firstName} {lastName}</span></div><div>{street}</div><div>{street2}</div><div><span>{zipcode}</span> <span>{city}</span>, <span>{country_name}</span></div><div>&nbsp;</div><div>{mail}</div><div>Tel.: <span>{phone}</span>, Fax: <span>{[values.fax||"-"]}</span></div>';
Signavio.I18N.Repository.Purchase.purchase = "Purchase user accounts";
Signavio.I18N.Repository.Purchase.purchaseLicenses = "<b>{[values.licenseCount]}</b> user account{[values.licenseCount>1?\"s\":\"\"]} for {[Signavio.I18N.Repository.Purchase[values.licenseType]]}";
Signavio.I18N.Repository.Purchase.purchaseLicensesAdditional ="Additional <b>{[values.licenseCount]}</b> user account{[values.users.length>1?\"s\":\"\"]} for {[Signavio.I18N.Repository.Purchase[values.licenseType]]}";
Signavio.I18N.Repository.Purchase.purchaseInterval = "";
Signavio.I18N.Repository.Purchase.purchaseFirstPayment = 'First payment interval is from the <b>{startDate}</b> to the <b>{firstIntervalEnd}</b> ({monthLeft} month{[values.monthLeft==1?"":"s"]} and {daysLeft} day{[values.daysLeft==1?"":"s"]}) for <span class="x-nowrap"><b>{sumFirst}</b> {currency}</span>. In case you already used a full 30 day trial in another Signavio workspace, we might correct the beginning of the payment interval to match the order date.';
Signavio.I18N.Repository.Purchase.purchaseFollowedPayment = "Subsequent invoices for all purchased accounts are <span class='x-nowrap'><b>{sum}</b> {currency}</span> <b>{[Signavio.I18N.Repository.Purchase[values.paymentInterval].toLowerCase()]}</b>.";
Signavio.I18N.Repository.Purchase["payment_invoice"] = Signavio.I18N.Repository.Purchase.billingDesc;
Signavio.I18N.Repository.Purchase["payment_paypal"] = Signavio.I18N.Repository.Purchase.billingPaypal;
Signavio.I18N.Repository.Purchase["payment_creditcard"] = Signavio.I18N.Repository.Purchase.billingCreditcard;
Signavio.I18N.Repository.Purchase.purchasePaymentMethodPayment = "The payment method is via <b>{[Signavio.I18N.Repository.Purchase[values.paymentmethod]]}</b>.";
Signavio.I18N.Repository.Purchase.purchaseHint = "All amounts plus local taxes. Cancellation notice at least five days prior to the end of a payment interval.";
Signavio.I18N.Repository.Purchase.agb = 'I hereby accept the <a href="http://www.signavio.com/en/terms-conditions.html" target="_blank">Terms and Conditions</a>.';
Signavio.I18N.Repository.Purchase.errorValidating = "Validating went wrong!<br/>Please go back and try it again.";
Signavio.I18N.Repository.Purchase.sending = "Sending...";
Signavio.I18N.Repository.Purchase.errorPurchase = "Purchase went wrong!<br/>Please go back and try it again.";
Signavio.I18N.Repository.Purchase.submit = "Purchase";
Signavio.I18N.Repository.Purchase.complete = "<div><b>Purchase complete.</b></div><div>A purchase confirmation was sent to your email address.<br/>The invoice will be sent to the same email address shortly.</div><br/><div>If you have any questions regarding the contract, please contact <a href='mailto:[supportMailAdresse]' target='_blank'>[supportMailAdresse]</a>.</div><br/><div>To activate the new accounts for the Signavio Process Editor you have to <a href='/p/explorer'>reload</a> the page.</div>";
Signavio.I18N.Repository.Purchase.completeExpired = "<div><b>Purchase complete.</b></div><div>A purchase confirmation was sent to your email address.</div><br/><div>Do you have any questions regarding the contract, please contact us through <a href='mailto:[supportMailAdresse]' target='_blank'>[supportMailAdresse]</a>.</div><br/><div>To use the Signavio Process Editor you have to <a href='/p/explorer'>reload</a> the site.</div>";
Signavio.I18N.Repository.Purchase.nextBtnLabel = "Next >";
Signavio.I18N.Repository.Purchase.previousBtnLabel = "< Previous";
Signavio.I18N.Repository.Purchase.close = "Close";
Signavio.I18N.Repository.Purchase.purchaseMenu = "Purchase user accounts";
Signavio.I18N.Repository.Purchase.expiredMessage = "Hi {name},<br/><br/>unfortunately, your free 30 day trial for the Signavio Process Editor has already expired. We hope you like this unique product.<br/><br/>By clicking the \"Next\" button, you can proceed with booking user accounts at our competitive rates. As you have experienced it with Signavio so far, you will not have to think about any complicated IT infrastructure for your modeling team at any point.<br/><br/>For further assistance regarding the booking process, please feel free to contact us:<br/><br/>Email: <a href='mailto:[supportMailAdresse]' target='_blank'>[supportMailAdresse]</a><br/>Phone: +49 30 8562154-21<br/><br/>Best regards,<br/>the Signavio Team";

Signavio.I18N.Repository.Purchase.notSupported = "For security reasons, only the creator of the workspace is able to purchase user licenses. Please contact <b>{admin}</b> in order to purchase user accounts for this workspace or contact the Signavio support team at <a href=\"mailto:[supportMailAdresse]\">[supportMailAdresse]</a> (Tel. +49 30 8562154-21).";

Signavio.I18N.Repository.Purchase.salesMessage = 	"You are interested in buying licenses for the Signavio Editor.<br/>" +
														"<br/>" +
														"Please contact your responsible sales person to purchase user account. " +
														"If you don't have any contact to a sales person yet, please send a mail to <a href='mailto:sales@signavio.com' target='_blank'>sales@signavio.com</a> or call +49 30 8562154-0.<br/>" +
														"<br/>" +
														"We are looking forward to hear from you!";

Signavio.I18N.Repository.Purchase.closeDescription = "Do you really want to abort the purchase?";

if(!Signavio.I18N.Repository.TalkAbout) Signavio.I18N.Repository.TalkAbout = {};
Signavio.I18N.Repository.TalkAbout.title = "Discuss";
Signavio.I18N.Repository.TalkAbout.titleEdit = "Show comments";
Signavio.I18N.Repository.TalkAbout.description = "Show the comments of the diagram.";
// EXTENSIONS

// HOVERBUTTON
if(!Signavio.I18N.Repository.HoverButton) Signavio.I18N.Repository.HoverButton = {};
Signavio.I18N.Repository.HoverButton.deleteString = "Delete";

if(!Signavio.I18N.Repository.FormStorePanel) Signavio.I18N.Repository.FormStorePanel = {};
Signavio.I18N.Repository.FormStorePanel.commit = "Save";
Signavio.I18N.Repository.FormStorePanel.reject = "Discard";
Signavio.I18N.Repository.FormStorePanel.language = "";

if(!Signavio.I18N.Repository.Hint) Signavio.I18N.Repository.Hint = {};
Signavio.I18N.Repository.Hint.supportedBrowserEditor = "Currently, modeling is not supported for your browser (see <a href='http://www.signavio.com/en/browser-compatibility.html' target='_blank'>browser compatibility</a>).<br/>Please use Firefox 3.6 or our Thin Client for Windows.";

// HR-PLANNING
Signavio.I18N.Repository.Offer.hrNeedTitle = "HR planning";
Signavio.I18N.Repository.Offer.hrNeedDescription = "Calculates the human resource need. The result will be returned as an Excel sheet.";

if(!Signavio.I18N.Repository.HrNeed) Signavio.I18N.Repository.HrNeed = {};
Signavio.I18N.Repository.HrNeed.title = "Human resource planning";
Signavio.I18N.Repository.HrNeed.summary = "Calculation was successful for #{nr1} of #{nr2} diagrams.";
Signavio.I18N.Repository.HrNeed.link = "Please click here to open the Excel file.";
Signavio.I18N.Repository.HrNeed.errors = "The following diagrams could not be handled:";
Signavio.I18N.Repository.HrNeed.onlyErrors = "Calculation was unsuccessful for all #{nr} diagrams.";
Signavio.I18N.Repository.HrNeed.onlyErrors2 = "The following list shows the errors in these diagrams.";
Signavio.I18N.Repository.HrNeed.openModel = "Open diagram";
Signavio.I18N.Repository.HrNeed.model = "Diagram";
Signavio.I18N.Repository.HrNeed.someRegionsWarning = "Warning: As only some diagrams use separate regions for start frequencies, these regions were not included in the calculation.";
Signavio.I18N.Repository.HrNeed.selectAll = "Select all";
Signavio.I18N.Repository.HrNeed.deselectAll = "Deselect all";
Signavio.I18N.Repository.HrNeed.unknownError = "An unknown error occurred. Please contact the support.";

if(!Signavio.I18N.Repository.ReturnCode) Signavio.I18N.Repository.ReturnCode = {};
Signavio.I18N.Repository.ReturnCode.HRNC_SUCCESS = "calculation successful";
Signavio.I18N.Repository.ReturnCode.HRNC_ERR_JSON = "diagram could not be loaded";
Signavio.I18N.Repository.ReturnCode.HRNC_ERR_SYNTAX = "diagram contains syntactical errors";
Signavio.I18N.Repository.ReturnCode.HRNC_ERR_PLAUS = "diagram contains errors";
Signavio.I18N.Repository.ReturnCode.HRNC_ERR_CALCULATION = "calculation failed";
Signavio.I18N.Repository.ReturnCode.EPC_PLAUS_NO_LABEL_FOR_NODE = "missing name";
Signavio.I18N.Repository.ReturnCode.EPC_PLAUS_NO_FREQUENCY = "no/incorrect frequency set";
Signavio.I18N.Repository.ReturnCode.EPC_PLAUS_0_POSITION_FOR_FUNC = "missing position";
Signavio.I18N.Repository.ReturnCode.EPC_PLAUS_SEVERAL_POSITIONS_FOR_FUNC = "too many positions used";
Signavio.I18N.Repository.ReturnCode.EPC_PLAUS_NO_TIME_FOR_FUNC = "no/incorrect execution time set";
Signavio.I18N.Repository.ReturnCode.EPC_PLAUS_PROB_SUM_NOT_1 = "invalid arc probabilities";
Signavio.I18N.Repository.ReturnCode.EPC_PLAUS_PROB_NOT_BETWEEN_0_AND_1 = "invalid arc probabilities";
Signavio.I18N.Repository.ReturnCode.EPC_PLAUS_OR_CONNECTOR_CONTAINED = "containing OR connector";
Signavio.I18N.Repository.ReturnCode.EPC_PLAUS_DEAD_LOCK_IN_MODEL = "containing deadlock";
Signavio.I18N.Repository.ReturnCode.EPC_PLAUS_ENDLESS_LOOP_IN_MODEL = "containing endless loop";
Signavio.I18N.Repository.ReturnCode.EPC_PLAUS_MULTI_MERGE_IN_MODEL = "containing multi merge";
Signavio.I18N.Repository.ReturnCode.EPC_PLAUS_MIXED_REGION_USAGE = "containing mixed frequency definitions";
Signavio.I18N.Repository.ReturnCode.EPC_PLAUS_NO_START_EVENT = "no start event";

Signavio.I18N.Repository.ReturnCode.ANALYSIS_SUCCESS = "calculation successful";
Signavio.I18N.Repository.ReturnCode.ANALYSIS_ERR_MODEL_NOT_EPC_OR_BPMN = "diagram is not EPC nor BPMN 2.0";
Signavio.I18N.Repository.ReturnCode.ANALYSIS_ERR_JSON = "diagram could not be loaded";
Signavio.I18N.Repository.ReturnCode.ANALYSIS_ERR_SYNTAX = "diagram contains syntactical errors";
Signavio.I18N.Repository.ReturnCode.ANALYSIS_ERR_PLAUS = "diagram contains errors";
Signavio.I18N.Repository.ReturnCode.ANALYSIS_ERR_CYCLE = "diagram contains cyclic process links";
Signavio.I18N.Repository.ReturnCode.ANALYSIS_ERR_CALCULATION = "calculation failed";
Signavio.I18N.Repository.ReturnCode.ANALYSIS_ERR_DEADLOCK_CALCULATION = "calculation failed due to deadlocks within the diagram";
Signavio.I18N.Repository.ReturnCode.ANALYSIS_ERR_ENDLESSLOOP_CALCULATION = "calculation failed due to endless loops within the diagram";
Signavio.I18N.Repository.ReturnCode.ANALYSIS_NOTHING_TO_APPLY = "diagram contains no elements to apply in calculation";

Signavio.I18N.Repository.HrNeed.nameLabel = "Name of scenario";
Signavio.I18N.Repository.HrNeed.nameDefault = "Scenario";
Signavio.I18N.Repository.HrNeed.paLabel = "Personal allowance time (in %)";
Signavio.I18N.Repository.HrNeed.futureStoreLabel = "Store for future calculations";
Signavio.I18N.Repository.HrNeed.minuteLabel = "Minutes per employee per year";
Signavio.I18N.Repository.HrNeed.startCalculationLabel = "Start calculation";
Signavio.I18N.Repository.HrNeed.error = "Error";
Signavio.I18N.Repository.HrNeed.nameError = "Scenario name is not set";
Signavio.I18N.Repository.HrNeed.minutesError = "Minutes per employee per year are not set correctly";
Signavio.I18N.Repository.HrNeed.paError = "Personal allowance time is not set correctly";
Signavio.I18N.Repository.HrNeed.selectionDescripton = "Please select the EPC diagrams that should be included in the calculation.";
Signavio.I18N.Repository.HrNeed.selectionButton = "Next";

Signavio.I18N.Repository.HrNeed.noModelSelected = "You did not select any diagram.";
Signavio.I18N.Repository.HrNeed.tooManyModelsSelected = "You selected more than #{maxnr} diagrams.";

//IMPORT
if(!Signavio.I18N.Import) Signavio.I18N.Import = {};
Signavio.I18N.Repository.Offer.importTitle = "Import / Export";
Signavio.I18N.Repository.Offer.importJSONTitle = "JSON";
Signavio.I18N.Repository.Offer.importJSONDescription = "Import a diagram in JSON format.";
Signavio.I18N.Repository.Offer.importAMLTitle = "Import ARIS® markup language";
Signavio.I18N.Repository.Offer.importAMLDescription = "Import of ARIS® repositories in AML format.";
Signavio.I18N.Repository.Offer.exportAMLTitle = "Export ARIS® markup language";
Signavio.I18N.Repository.Offer.exportAMLDescription = "Export of an ARIS® file in AML format.";
Signavio.I18N.Repository.Offer.importAMLDialogHeader = "ARIS® markup language import (AML)";
Signavio.I18N.Repository.Offer.importAMLDialogText = "The import of ARIS® markup language import (AML) files is not available in the free trial phase by default. Please contact us at <a href='mailto:[supportMailAdresse]'>[supportMailAdresse]</a> and we will enable this functionality for you without extra cost for your trial period.";

Signavio.I18N.Import.select = "Select the JSON file you want to upload:";
if(!Signavio.I18N.Import.dialog) Signavio.I18N.Import.dialog = {};
Signavio.I18N.Import.dialog.title = "Import JSON";
Signavio.I18N.Import.dialog.ok = "Import";
Signavio.I18N.Import.dialog.wait = "Please wait while uploading...";
Signavio.I18N.Import.dialog.failed = "It is not possible to load this file.";
Signavio.I18N.Import.dialog.close = "Cancel";

Signavio.I18N.Repository.Offer.importXPDLTitle = "Import of XPDL 2.1";
Signavio.I18N.Repository.Offer.importXPDLDescription = "Import of files in the XPDL 2.1 format.";
Signavio.I18N.Repository.Offer.importXPDLLabel = "Please select the XPDL 2.1 file to be imported. The diagram will be opened. Please note that you will need to save the diagram manually in order to save it to the current folder.";
Signavio.I18N.Repository.Offer.importXPDLWait = "Please wait while your file is being uploaded.<br/>This might take some time...";
Signavio.I18N.Repository.Offer.importXPDLNotSupportedTitle = "Not supported browser";
Signavio.I18N.Repository.Offer.importXPDLNotSupportedDescription = "Currently, the import of XPDL Files is not supported for your browser (see <a href='http://www.signavio.com/en/browser-compatibility.html' target='_blank'>browser compatibility</a>).<br/>Please use Firefox 3.6 or our Thin Client for Windows.";
Signavio.I18N.Repository.Offer.importXPDLFileMissingTitle = "No valid file";
Signavio.I18N.Repository.Offer.importXPDLFileMissingDescription = "Please define a valid XPDL 2.1 XML file.";
Signavio.I18N.Repository.Offer.importXPDLTooBigTitle = "File is to large";
Signavio.I18N.Repository.Offer.importXPDLTooBigDescription = "The used file is larger than 10 mb. Please use files with less size.";
Signavio.I18N.Repository.Offer.importXPDLWarningTitle = "Import went wrong";
Signavio.I18N.Repository.Offer.importXPDLWarningDescription = "The import of the XPDL file went wrong. Please try again or contact the <a href='mailto:[supportMailAdresse]'>support</a>.";
Signavio.I18N.Repository.Offer.importXPDLLinkListDescription = "There are multiple processes contained in the XPDL file. They are accessible over the list of links below. CAUTION: Each process must be opened and saved to be stored permanently in the current directory.";
Signavio.I18N.Repository.Offer.importXPDLLinkListTitle = "Diagram links";
Signavio.I18N.Repository.Offer.importXPDLLinkListBtn = "Close";
Signavio.I18N.Repository.Offer.importXPDLLinkListProcess = "Process";
Signavio.I18N.Repository.Offer.importXPDLWrongLicense = "The import of XPDL files is only available in the Professional Edition. For further information about the Basic or Professional Edition please follow <a href='http://signavio.com/en/produkte/process-editor-as-a-service.html' target='_blank'>this</a> link.";
Signavio.I18N.Repository.Offer.importXPDLNotInTrial = "The import of XPDL files is not available in the free trial phase by default. Please contact us at <a href='mailto:[supportMailAdresse]'>[supportMailAdresse]</a> and we will enable this functionality for you without extra cost for your trial period.";
Signavio.I18N.Repository.Offer.XPDLImport = {};
Signavio.I18N.Repository.Offer.XPDLImport.CouldNotGenerateSvgForDiagramTitle = "Creating the graphical representation failed";
Signavio.I18N.Repository.Offer.XPDLImport.CouldNotGenerateSvgForDiagram = "Creating the graphical representation failed. Please send your XPDL 2.0 XML file to the Signavio <a href='mailto:[supportMailAdresse]'>support</a>.";
Signavio.I18N.Repository.Offer.XPDLImport.CouldNotBuildDiagramObjectFromJSON = "Creating the graphical representation failed, because the diagram object could not be built correctly. Please send your XPDL 2.0 XML file to the Signavio <a href='mailto:[supportMailAdresse]'>support</a>.";

Signavio.I18N.Repository.Offer.importVisioTitle = "Import of Visio";
Signavio.I18N.Repository.Offer.importVisioDescription = "Import of files in the Visio VDX Format.";
Signavio.I18N.Repository.Offer.importVisioNotSupportedTitle = "Not supported browser";
Signavio.I18N.Repository.Offer.importVisioNotSupportedDescription = "Currently, the import of Visio files is not supported for your browser (see <a href='http://www.signavio.com/en/browser-compatibility.html' target='_blank'>browser compatibility</a>).<br/>Please use Firefox 3.6 or our Thin Client for Windows.";
Signavio.I18N.Repository.Offer.importVisioLabel = "Please select the Visio (.vdx) file to be imported. The diagram will be opened. Please note that you will need to save the diagram manually in order to save it to the current folder.";
Signavio.I18N.Repository.Offer.importVisioStencil = "Please define which stencilset is used in the Visio file.";
Signavio.I18N.Repository.Offer.importVisioFileMissingTitle = "No valid file";
Signavio.I18N.Repository.Offer.importVisioFileMissingDescription = "Please define a valid Visio VDX file.";
Signavio.I18N.Repository.Offer.importVisioTooBigTitle = "File is too large";
Signavio.I18N.Repository.Offer.importVisioTooBigDescription = "The used file is larger than 10 mb. Please use files with less size.";
Signavio.I18N.Repository.Offer.importVisioWarningTitle = "Import went wrong";
Signavio.I18N.Repository.Offer.importVisioWarningDescription = "The import of the Visio file went wrong. Please try again or contact the <a href='mailto:[supportMailAdresse]'>support</a>.";
Signavio.I18N.Repository.Offer.importVisioWrongLicense = "The import of Visio VDX files is only available in the Professional Edition. For further information about the Basic or Professional Edition please follow <a href='http://signavio.com/en/produkte/process-editor-as-a-service.html' target='_blank'>this</a> link.";

//ProcessDocumentation
Signavio.I18N.Repository.Offer.processDocumentationTitle = "Process documentation (PDF)";
Signavio.I18N.Repository.Offer.processDocumentationDesc = "Exports a set of diagrams to a PDF process documentation.";
Signavio.I18N.Repository.Offer.processDocumentationWordTitle = "Process documentation (Word)";
Signavio.I18N.Repository.Offer.processDocumentationWordDesc = "Exports a set of diagrams to a Microsoft Word process documentation.";
Signavio.I18N.Repository.Offer.processDocumentationWordInfoInfo = "Important Information";
Signavio.I18N.Repository.Offer.processDocumentationWordInfoDesc = "To update the page count in the table of content of the word document, please select the table of content and use the F9 key or the function 'Refresh fields' in the context menu.";
Signavio.I18N.Repository.Offer.processDocumentationDefault = "Predefined templates";
Signavio.I18N.Repository.Offer.processDocumentationCoorporate = "Corporate templates";
Signavio.I18N.Repository.Offer.processDocumentationSelectLabel = "Please select the template which should be used for the process documentation.";
Signavio.I18N.Repository.Offer.processDocumentationTemplateTitle = "Template";
Signavio.I18N.Repository.Offer.processDocumentationConfigurationTitle = "Configuration";
Signavio.I18N.Repository.Offer.processDocumentationSelectDiagrams = "Please choose at least one model, for which the process documentation should be created.";
Signavio.I18N.Repository.Offer.processDocumentationSelectDiagramsForReport = "Please choose at least one model, for which the spreadsheet report should be created.";
Signavio.I18N.Repository.Offer.processDocumentationSelectGuideline = "Please choose a convention, for which the spreadsheet report should be created.";
Signavio.I18N.Repository.Offer.processDocumentationCreateExampleTemplateTitle = "Create Example Template";
Signavio.I18N.Repository.Offer.processDocumentationCreateExampleTemplateDescription = "Creates an example diagram of type process documentiton template.";

if(!Signavio.I18N.Repository.ProcessDocu) Signavio.I18N.Repository.ProcessDocu = {};
Signavio.I18N.Repository.ProcessDocu.select = "Generate a printable offline documentation with detailed information on the diagrams and their modeling elements as a PDF file. Please select all diagrams and folders to be included in the process documentation.";
Signavio.I18N.Repository.ProcessDocu.selectWord = "Generate a printable offline documentation with detailed information on the diagrams and their modeling elements as a Microsoft Word file. Please select all diagrams and folders to be included in the process documentation.";
Signavio.I18N.Repository.ProcessDocu.further = "Continue";
Signavio.I18N.Repository.ProcessDocu.generate = "Generate documentation";
Signavio.I18N.Repository.ProcessDocu.title = "Title";
Signavio.I18N.Repository.ProcessDocu.defaultTitle = "Process documentation";
Signavio.I18N.Repository.ProcessDocu.tenant = "Workspace";
Signavio.I18N.Repository.ProcessDocu.tenantString = "{name}'s Signavio workspace";
Signavio.I18N.Repository.ProcessDocu.org = "Organization";
Signavio.I18N.Repository.ProcessDocu.author = "Author";
Signavio.I18N.Repository.ProcessDocu.date = "Date";
Signavio.I18N.Repository.ProcessDocu.version = "Version";
Signavio.I18N.Repository.ProcessDocu.clip = "Split diagrams across pages";
Signavio.I18N.Repository.ProcessDocu.intro = "Introduction";
Signavio.I18N.Repository.ProcessDocu.branding = "Show Signavio logo";
Signavio.I18N.Repository.ProcessDocu.sketchy = "Use sketchy mode (BETA)";
Signavio.I18N.Repository.ProcessDocu.blackWhite = "Show in black/white";
Signavio.I18N.Repository.ProcessDocu.premiumOnly = "This feature is only available in the Professional or Corporate Edition.";
Signavio.I18N.Repository.ProcessDocu.waiting = "Process documentation will be created.<br/>This might take some time...";
Signavio.I18N.Repository.ProcessDocu.template = "Template";
Signavio.I18N.Repository.ProcessDocu.templatepdfstylesheet = "Signavio (old layout)";
Signavio.I18N.Repository.ProcessDocu.templatepdfstylesheetswisspost = "Swiss Post Template";
Signavio.I18N.Repository.ProcessDocu.templatepdfstylesheetkaiserkraft = "Kaiser Kraft Template";
Signavio.I18N.Repository.ProcessDocu.exportOptionsTitle = "Options";
Signavio.I18N.Repository.ProcessDocu.exportOptionsDesc = "";
Signavio.I18N.Repository.ProcessDocu.treePage = "Change diagram selection";
Signavio.I18N.Repository.ProcessDocu.optionPage = "Define Export options";
Signavio.I18N.Repository.ProcessDocu.languageFieldLabel = "Language";
Signavio.I18N.Repository.ProcessDocu.titleOptions = "Options";
Signavio.I18N.Repository.ProcessDocu.descriptionViewExport = "Please choose the language in which the diagram should be exported.";

Signavio.I18N.Repository.Offer.teachingTitle = "BPM teaching material";
Signavio.I18N.Repository.Offer.teachingDesc = "The BPM Academic Initiative provides BPM teaching material for your lectures.";
Signavio.I18N.Repository.Offer.teachingOpenInNewWindow = "Open teaching material in a new window.";


// Archive
Signavio.I18N.Repository.Offer.archive = "Export Signavio archive (SGX)";
Signavio.I18N.Repository.Offer.archiveDesc = "Export all selected diagrams and folders.";
Signavio.I18N.Repository.Offer.archiveHead = "Export only the latest revision of each diagram";
Signavio.I18N.Repository.Offer.archiveLabel = "All selected diagrams and folders are exported to a single archive file. Please select the diagrams and folders, which should be exported:";
Signavio.I18N.Repository.Offer.archiveDefaultLanguageTitle = "Default language";
Signavio.I18N.Repository.Offer.archiveDefaultLanguage = "Please chosse here which language should be used as the default language in the exported archive.";
Signavio.I18N.Repository.Offer.exportLanguagesTitle = "Languages to export";
Signavio.I18N.Repository.Offer.exportLanguages = "Choose additional langauges that should be included in the exported archive.";
Signavio.I18N.Repository.Offer.archiveLanguageOptionsDescription = "Spacify additional options for how languages should be exported.";
Signavio.I18N.Repository.Offer.archiveBtn = "Export";
Signavio.I18N.Repository.Offer.archiveNoAdminHint = "Not all data might be imported, as you don't have workspace administrator permissions. For example, dictionary categories, definitions of custom attributes and perspectives won't be transferred.";

Signavio.I18N.Repository.Offer.archiveRestore = "Import Signavio archive (SGX)";
Signavio.I18N.Repository.Offer.archiveRestoreDesc = "Restore Signavio internal archive to the currently selected folder.";
Signavio.I18N.Repository.Offer.archiveRestoreErrorTitle = "Import Signavio diagrams";
Signavio.I18N.Repository.Offer.archiveRestoreErrorDesc = "Something went wrong during the upload. Please try again or contact the <a href='mailto:[supportMailAdresse]'>Support</a>.";
Signavio.I18N.Repository.Offer.archiveWarnings = "Warning, some or all of the diagrams may not be imported:";
Signavio.I18N.Repository.Offer.archiveRestoreWrongLicense = "The import of Signavio archive files (SGX) is only available in the Professional Edition. For further information about the Basic or Professional Edition please follow <a href='http://signavio.com/en/produkte/process-editor-as-a-service.html' target='_blank'>this</a> link.";
Signavio.I18N.Repository.Offer.archiveRestoreNotInTrial = "The import of Signavio archive files (SGX) is not available in the free trial phase by default. Please contact us at <a href='mailto:[supportMailAdresse]'>[supportMailAdresse]</a> and we will enable this functionality for you without extra cost for your trial period.";
Signavio.I18N.Repository.Offer.noWriteForGlossaryImport = "Currently you are not allowed to import glossary items. You need the specific right to edit glossary items.";

Signavio.I18N.Repository.Offer.archiveRestoreFiles = "Import contained Files/Pictures";
Signavio.I18N.Repository.Offer.archiveRestoreLabel = "Please select the Signavio export archive to be imported. All contents are imported into the current folder. For further informationen please take a look at our user manual.";
Signavio.I18N.Repository.Offer.archiveRestoreBtn = "Import";
Signavio.I18N.Repository.Offer.archiveRestoreWait = "Please wait while your diagrams are being imported.<br/>This might take some time...";

Signavio.I18N.Repository.Offer.archiveRestoreImportGlossaryInfo = "You can optionally import dictionary entries that are used in the imported diagrams. If you select the overwrite option, existing dictionary entries with the same title will be replaced.";
Signavio.I18N.Repository.Offer.archiveRestoreImportGlossary = "Import dictionary entries";
Signavio.I18N.Repository.Offer.archiveRestoreOverwriteGlossary = "Overwrite existing dictionary entries with the same title";
Signavio.I18N.Repository.Offer.archiveRestoreImportMetaData = "Import own attributes";
Signavio.I18N.Repository.Offer.archiveRestoreOverwriteMetaData = "Overwrite existing settings of own attributes";
Signavio.I18N.Repository.Offer.archiveRestoreImportStencilSetPerspective = "Import modeling language subsets";
Signavio.I18N.Repository.Offer.archiveRestoreOverwriteStencilSetPerspective = "Overwrite modeling language subsets of the same name";
Signavio.I18N.Repository.Offer.archiveRestoreOwnAttributes = "Import custom attribtues and modelling language configurations";

Signavio.I18N.Repository.Offer.archiveRestoreLanguageNothingDefined = "The imported archive contains no language definition. All multilanguage text values are assigned to the default language ({language}).";
Signavio.I18N.Repository.Offer.archiveRestoreLanguageMoreDefined = "The imported archive contains more language definitions than activated in your workspace. You should activate following languages in order to see all the imported content:<br/>{languages}";
Signavio.I18N.Repository.Offer.archiveRestoreLanguageOnlyDefined = "The imported archive contains language definitions, but there are no language definitions activated in your workspace. The default language ({language}) of the archive will be used. All other languages ({languages}) will only be shown if you activate them in your workspace.";
Signavio.I18N.Repository.Offer.archiveRestoreLanguageNoMatching = "The import has been canceled!<br/>The imported archive contains other language definitions than your workspace. Please activate at least one of the following languages and import the archive again:<br/>{languages}";
Signavio.I18N.Repository.Offer.archiveRestoreFileQuotaException = "The import has been canceled!<br/>The imported archive contains documents/pictures which are too large or would exceed the size of your Signavio file storage. You can either skip the import of documents/pictures or contact the Signavio Support to order additional storage.";


// AMLImport ARIS® Markup Language (AML)
Signavio.I18N.Repository.Offer.amlImportRestore = "Import diagrams from AML archive";

Signavio.I18N.Repository.Offer.amlImportRestoreLabel = "The ARIS® Markup Language (AML) is the XML export format of the software tool ARIS®. Please select the XML file to be imported. All contents (event-driven process chains, value chains, and organization charts) are imported into the currently selected folder. The maximum file size allowed is 5MB.";
Signavio.I18N.Repository.Offer.amlImportRestoreBtn = "Import";
Signavio.I18N.Repository.Offer.amlImportRestoreWait = "Please wait while your diagrams are being imported.<br/>This might take some time...";

Signavio.I18N.Repository.Offer.amlImportAsyncInfoTitle = "The import started successfully";
Signavio.I18N.Repository.Offer.amlImportAsyncInfoDesc = "The AML archive import is running in the background and may take while. You will be notified by mail after the import finished. You can continue using the system as usual during the import.";

Signavio.I18N.Repository.Offer.amlImportValidationFailedTitle = "The validation of your AML file failed";
Signavio.I18N.Repository.Offer.amlImportValidationFailedDesc = "The AML file could not be validated. Please contact the <a href='mailto:[supportMailAdresse]'>Support</a> to solve this problem.";

Signavio.I18N.Repository.Offer.amlImportFileTooBigTitle = "Maximum file size of 5MB exceeded";
Signavio.I18N.Repository.Offer.amlImportFileTooBigDesc = "The maximum size allowed for an AML file is 5MB. Try to split your ARIS® process repository into smaller chunks. If you need to import bigger files, please contact the <a href='mailto:[supportMailAdresse]'>Support</a>.";

Signavio.I18N.Repository.Offer.amlImportWait = "Please wait while your diagrams are being uploaded.<br/>This might take some time...";

Signavio.I18N.Repository.Offer.amlImportSuccess = "The import of the ARIS® Markup Language (AML) finished successfully. {foldercount} folders, {diagramcount} diagrams, and {glossarycount} dictionary entries have been imported.";

Signavio.I18N.Repository.Offer.amlImportErrorTitle = "The file cannot be processed";
Signavio.I18N.Repository.Offer.amlImportErrorDesc = "The file cannot be processed. Please check, if the file is a valid ARIS® Markup Language (AML) file. If you cannot solve the problem, please contact the <a href='mailto:[supportMailAdresse]'>Support</a>.";


Signavio.I18N.Repository.Offer.glossaryTitle = "Dictionary";
Signavio.I18N.Repository.Offer.glossaryDescription = "Click to open the dictionary in a new window.";


//Deploy2Tim
if(!Signavio.I18N.Repository.Deploy2Tim) {Signavio.I18N.Repository.Deploy2Tim = {};}
Signavio.I18N.Repository.Deploy2Tim.title = "Deploy to T!M";
Signavio.I18N.Repository.Deploy2Tim.description = "Deploy the selected process diagram to the T!M server.";
Signavio.I18N.Repository.Deploy2Tim.suc = "Deployment to T!M server was successful.";
Signavio.I18N.Repository.Deploy2Tim.fail = "Deployment to T!M failed.";
Signavio.I18N.Repository.Deploy2Tim.syntax = "The diagram could not be deployed to T!M, as the model checking showed <b>syntax errors</b>.";
Signavio.I18N.Repository.Deploy2Tim.stencilset = "The diagram cannot be deployed to T!M, as it is no Human Workflow model.";
Signavio.I18N.Repository.Deploy2Tim.transform = "The diagram could not be transformed. Please contact the support.";
Signavio.I18N.Repository.Deploy2Tim.plausibility = "The diagram could not be deployed to T!M, as the model checking showed <b>plausibility errors</b>.";
Signavio.I18N.Repository.Deploy2Tim.checkFail2 = "Please open the <a href=\"#{link}\" target=\"_blank\">diagram with the editor</a> in order to fix the problems.";
Signavio.I18N.Repository.Deploy2Tim.checkFail3 = "In the opened editor, errors can be visualized by clicking the <img src=\"#{image}\"> button.";
Signavio.I18N.Repository.Deploy2Tim.deploy = "Deploy";
Signavio.I18N.Repository.Deploy2Tim.endPoint = "Endpoint";

Signavio.I18N.Repository.Deploy2Tim.deployedCurrentRevision = "This revision has been deployed to the T!M server";
Signavio.I18N.Repository.Deploy2Tim.deplozedOlderRevision = "An older revision has been deployed to the T!M server";

//Deploy2Saperion
if(!Signavio.I18N.Repository.Deploy2Saperion) {Signavio.I18N.Repository.Deploy2Saperion = {};}
Signavio.I18N.Repository.Deploy2Saperion.title = "Deploy to SAPERION";
Signavio.I18N.Repository.Deploy2Saperion.description = "Deploy the selected process diagram to the SAPERION server.";
Signavio.I18N.Repository.Deploy2Saperion.suc = "Deployment to SAPERION server was successful. At <b>#{time}</b>, diagram revision <b>#{buildNr}</b> was deployed.";
Signavio.I18N.Repository.Deploy2Saperion.fail = "Deployment to SAPERION failed.";
Signavio.I18N.Repository.Deploy2Saperion.failConnect = "Deployment to SAPERION failed. Could not connect to the server.";
Signavio.I18N.Repository.Deploy2Saperion.syntax = "The diagram could not be deployed to SAPERION, as the model checking showed <b>syntax errors</b>.";
Signavio.I18N.Repository.Deploy2Saperion.stencilset = "The diagram cannot be deployed to SAPERION, as it is no BPMN 2.0 process diagram.";
Signavio.I18N.Repository.Deploy2Saperion.transform = "The diagram could not be transformed. Please contact the support.";
Signavio.I18N.Repository.Deploy2Saperion.plausibility = "The diagram could not be deployed to SAPERION, as the model checking showed <b>plausibility errors</b>.";
Signavio.I18N.Repository.Deploy2Saperion.checkFail2 = "Please open the <a href=\"#{link}\" target=\"_blank\">diagram with the editor</a> in order to fix the problems.";
Signavio.I18N.Repository.Deploy2Saperion.checkFail3 = "In the opened editor, errors can be visualized by clicking the <img src=\"#{image}\"> button.";
Signavio.I18N.Repository.Deploy2Saperion.deploy = "Deploy";
Signavio.I18N.Repository.Deploy2Saperion.endPoint = "Endpoint";
Signavio.I18N.Repository.Deploy2Saperion.client = "Client";
Signavio.I18N.Repository.Deploy2Saperion.type = "Type";
Signavio.I18N.Repository.Deploy2Saperion.deployFail1 = "The diagram could not be deployed to SAPERION due to the following errors:";
Signavio.I18N.Repository.Deploy2Saperion.deployFail2 = "The diagram could not be deployed to SAPERION due to errors during transformation. The following diagram shows these errors: ";
Signavio.I18N.Repository.Deploy2Saperion.authentificationFailedTitle = "Authentification failed";
Signavio.I18N.Repository.Deploy2Saperion.authentificationFailedDescription = "The authentification went wrong. Please use a correct username/password combination.";
Signavio.I18N.Repository.Deploy2Saperion.progressTitle = "SAPERION";
Signavio.I18N.Repository.Deploy2Saperion.progressDescription = "Deployment is in progress";
Signavio.I18N.Repository.Deploy2Saperion.version = "Version";
Signavio.I18N.Repository.Deploy2Saperion.versionNewTitle = "Save as a new version";
Signavio.I18N.Repository.Deploy2Saperion.versionNewDescription = "All running process instances are still work after the latest process, however new instances get startet with the new one.";
Signavio.I18N.Repository.Deploy2Saperion.versionCurTitle = "Overwrite current version";
Signavio.I18N.Repository.Deploy2Saperion.versionCurDescription = "With overwriting the current version all running and new instances are proceed with this process version.";

Signavio.I18N.Repository.Deploy2Saperion.deployedCurrentRevision = "This revision has been deployed to the SAPERION server";
Signavio.I18N.Repository.Deploy2Saperion.deplozedOlderRevision = "An older revision has been deployed to the SAPERION server";

if(!Signavio.I18N.Repository.Deploy2Saperion.ReturnCode) {Signavio.I18N.Repository.Deploy2Saperion.ReturnCode = {};}
Signavio.I18N.Repository.Deploy2Saperion.ReturnCode.CONNECTOR_COMMUNICATION_ERROR = "The server's internal communication failed.";
Signavio.I18N.Repository.Deploy2Saperion.ReturnCode.NO_MULTIPART_REQUEST = "The request was invalid.";
Signavio.I18N.Repository.Deploy2Saperion.ReturnCode.NO_PARAMETER_PARSED = "Request parameters were missing.";
Signavio.I18N.Repository.Deploy2Saperion.ReturnCode.AUTHENTIFICATION_FAILED = "Authentification failed.";
Signavio.I18N.Repository.Deploy2Saperion.ReturnCode.INTERNAL_SYSTEM_ERROR = "An internal server error occurred.";

//Deploy2Carestation
if(!Signavio.I18N.Repository.Deploy2Carestation) {Signavio.I18N.Repository.Deploy2Carestation = {};}
Signavio.I18N.Repository.Deploy2Carestation.title = "Deploy to CareStation";
Signavio.I18N.Repository.Deploy2Carestation.description = "Deploy the selected process diagram to the CareStation server.";
Signavio.I18N.Repository.Deploy2Carestation.suc = "Deployment to CareStation server was successful.";
Signavio.I18N.Repository.Deploy2Carestation.fail = "Deployment to CareStation server failed due to an error within the WF Engine.";
Signavio.I18N.Repository.Deploy2Carestation.syntax = "The diagram could not be deployed to CareStation server, as the model checking showed <b>syntax errors</b>.";
Signavio.I18N.Repository.Deploy2Carestation.stencilset = "The diagram cannot be deployed to CareStation server, as it is no CareStation model.";
Signavio.I18N.Repository.Deploy2Carestation.transform = "The diagram could not be transformed. Please contact the support.";
Signavio.I18N.Repository.Deploy2Carestation.plausibility = "The diagram could not be deployed to CareStation server, as the model checking showed <b>plausibility errors</b>.";
Signavio.I18N.Repository.Deploy2Carestation.checkFail2 = "Please open the <a href=\"#{link}\" target=\"_blank\">diagram with the editor</a> in order to fix the problems.";
Signavio.I18N.Repository.Deploy2Carestation.checkFail3 = "In the opened editor, errors can be visualized by clicking the <img src=\"#{image}\"> button.";
Signavio.I18N.Repository.Deploy2Carestation.deploy = "Deploy";
Signavio.I18N.Repository.Deploy2Carestation.authentificationFailedTitle = "Authentification failed";
Signavio.I18N.Repository.Deploy2Carestation.authentificationFailedDescription = "The authentification went wrong. Please use a correct username/password combination.";

if(!Signavio.I18N.Repository.Deploy2Commed) {Signavio.I18N.Repository.Deploy2Commed = {};}
Signavio.I18N.Repository.Deploy2Commed.deployedCurrentRevision = "This revision has been deployed to the CareStation server";
Signavio.I18N.Repository.Deploy2Commed.deplozedOlderRevision = "An older revision has been deployed to the CareStation server";

// Deprecated start
//PDF Export
if(!Signavio.I18N.Repository.MapExport) {Signavio.I18N.Repository.MapExport = {};}
Signavio.I18N.Repository.MapExport.offerTitle ="Export PDF (single diagram)";
Signavio.I18N.Repository.MapExport.offerTitleDesc ="You can print your diagram across multiple pages. Please select the layout you would like to have for your PDF export.";
Signavio.I18N.Repository.MapExport.treeDesc = "Please select all diagrams and folders to be included in the process documentation.";
Signavio.I18N.Repository.MapExport.optionWindowTitle ="Export PDF";
Signavio.I18N.Repository.MapExport.buttonTitle = "Generate PDF";
Signavio.I18N.Repository.MapExport.closeTitle = "Cancel";
Signavio.I18N.Repository.MapExport.fitToPage = "Fit to one page";
Signavio.I18N.Repository.MapExport.clipAllSides = "Continuous print (2-dimensional)";
Signavio.I18N.Repository.MapExport.clipBottom="Continuous print (vertical)";
Signavio.I18N.Repository.MapExport.clipRight="Continuous print (horizontal)";
Signavio.I18N.Repository.MapExport.landscape="Landscape";
Signavio.I18N.Repository.MapExport.portrait="Portrait";
Signavio.I18N.Repository.MapExport.defaultName = "Export";
Signavio.I18N.Repository.MapExport.treePageTitle = "Change diagram selection";
Signavio.I18N.Repository.MapExport.optionPageTitle = "Define Export options";
Signavio.I18N.Repository.MapExport.waitMask = "Please wait...";
Signavio.I18N.Repository.MapExport.linkedSubprocessesTitle = "Export linked subprocesses";
Signavio.I18N.Repository.MapExport.linkedSubprocessesDesc = "Please select which linked subprocesses should be exported as well.";
Signavio.I18N.Repository.MapExport.linkedSubprocessesNone = "No linked subprocesses";
Signavio.I18N.Repository.MapExport.linkedSubprocessesAll = "Linked subprocesses of all levels";
Signavio.I18N.Repository.MapExport.linkedSubprocessesSome = "Linked subprocesses of the first level";
Signavio.I18N.Repository.MapExport.exportFormat = 'Page orientation';
Signavio.I18N.Repository.MapExport.furtherOptionsTitle = "Options";
Signavio.I18N.Repository.MapExport.furtherOptionsDesc = "Determine further export options";
Signavio.I18N.Repository.MapExport.selectViewOriginal = "<i>Original</i>";
Signavio.I18N.Repository.MapExport.selectViewTitle = "Select view";
Signavio.I18N.Repository.MapExport.selectViewDesc = "Please select the view you want to export:";
Signavio.I18N.Repository.MapExport.noModelsSelected = "Please select at least one diagram, that should be exported as PDF.";
// Deprecated end

if(!Signavio.I18N.Repository.PDFExport) { Signavio.I18N.Repository.PDFExport = {}; }
Signavio.I18N.Repository.PDFExport.title = "Export PDF";

// MetaDataEditor
if(!Signavio.I18N.Repository.MetaDataEditor) {Signavio.I18N.Repository.MetaDataEditor = {};}
Signavio.I18N.Repository.MetaDataEditor.offer="Meta data editor";
Signavio.I18N.Repository.MetaDataEditor.offerDesc="Meta data editor";

// Publishing
if(!Signavio.I18N.Repository.Publishing) {Signavio.I18N.Repository.Publishing = {};}
Signavio.I18N.Repository.Publishing.publishingWindowTitle = "Publish to process portal";
Signavio.I18N.Repository.Publishing.publishingWindowDesc = "Select which diagrams, documents, or pictures should be published in the Process portal by checking the elements or the folders.";
Signavio.I18N.Repository.Publishing.publishButtonTitle = "Publish";
Signavio.I18N.Repository.Publishing.confirmDialogTitle = "Publish to process portal";
Signavio.I18N.Repository.Publishing.confirmDialogTextPre = "Do you really want to publish ";
Signavio.I18N.Repository.Publishing.confirmDialogTextSingular = " file in the most recent revision?";
Signavio.I18N.Repository.Publishing.confirmDialogTextPlural = " files in the most recent revision?";
Signavio.I18N.Repository.Publishing.noModel = "No file selected";
Signavio.I18N.Repository.Publishing.emptyDirectory = "The selected directory contains no files.";
Signavio.I18N.Repository.Publishing.noCertTitle = "Publishing component required";
Signavio.I18N.Repository.Publishing.noCertText = "Please purchase the publishing component for your SaaS workspace in order to create your own process portal. You can find an overview of the different features "+
													"<a href='http://www.signavio.com/images/product-info/feature_overview.pdf' target='_blank'>here</a>."+
													"\r"+
													"If you like to order the publishing component please contact <a href='mailto:sales@signavio.com' target='_blank'>sales@signavio.com</a>.";

if(!Signavio.I18N.Repository.ExportWarnings) {Signavio.I18N.Repository.ExportWarnings = {};}
Signavio.I18N.Repository.ExportWarnings.BPMN20SERIALIZATION = "Note that the serialization format of BPMN 2.0 is not yet final and is probably subject to change (<a target='_blank' href='http://www.bpmn.org/'>http://bpmn.org/</a>).";
Signavio.I18N.Repository.ExportWarnings.BPMN20XPDLSERIALIZATION = "Note that the XPDL serialization format of BPMN 2.0 is not yet final and is probably subject to change.";

//jpdl4 Export
Signavio.I18N.Repository.Offer.jpdl4ExportTitle = "Export jPDL 4";
Signavio.I18N.Repository.Offer.jpdl4ExportDescription = "Transform to jPDL 4";
//jpdl4 Import
Signavio.I18N.Repository.Offer.jpdl4ImportTitle = "Import jPDL 4";
Signavio.I18N.Repository.Offer.jpdl4ImportDescription = "Import a diagram from a jPDL 4 file";
Signavio.I18N.Repository.Offer.jpdl4ImportFileField = "Please select an jPDL 4 process diagram you want to import.";
Signavio.I18N.Repository.Offer.jpdl4ImportErrorMsg = "The jPDL 4 file could not be imported.";
Signavio.I18N.Repository.Offer.jpdl4ImportWrongLicense = "The import of jPDL 4 files is only available in the Professional Edition. For further information about the Basic or Professional Edition please follow <a href='http://signavio.com/en/produkte/process-editor-as-a-service.html' target='_blank'>this</a> link.";
Signavio.I18N.Repository.Offer.jpdl4ImportNotInTrial = "The import of jPDL 4 files is not available in the free trial phase by default. Please contact us at <a href='mailto:[supportMailAdresse]'>[supportMailAdresse]</a> and we will enable this functionality for you without extra cost for your trial period.";




// META DATA
if(!Signavio.I18N.Repository.MetaData) {Signavio.I18N.Repository.MetaData = {};}
if(!Signavio.I18N.Repository.MetaData.Types) {Signavio.I18N.Repository.MetaData.Types = {};}

Signavio.I18N.Repository.MetaData.add = "New attribute";
Signavio.I18N.Repository.MetaData.Types.MetaDataStringInfo = "Single-line text";
Signavio.I18N.Repository.MetaData.Types.MetaDataStringInfoMulti = "Multi-line text";
Signavio.I18N.Repository.MetaData.Types.MetaDataGlossaryLink = "Dictionary link";
Signavio.I18N.Repository.MetaData.Types.MetaDataModelLink = "Diagram link";
Signavio.I18N.Repository.MetaData.Types.MetaDataUrl = "Document/URL";
Signavio.I18N.Repository.MetaData.Types.MetaDataDate = "Date";
Signavio.I18N.Repository.MetaData.Types.MetaDataEnum = "Drop-down box";
Signavio.I18N.Repository.MetaData.Types.MetaDataNumberInfo = "Number";
Signavio.I18N.Repository.MetaData.Types.MetaDataBoolean = "Boolean";
Signavio.I18N.Repository.MetaData.Types.MetaDataIks = "Risk and Controls"; 
Signavio.I18N.Repository.MetaData.Types.MetaDataComplex = "Table";

if(!Signavio.I18N.Repository.MetaData.AddDialog) { Signavio.I18N.Repository.MetaData.AddDialog = {}; }
Signavio.I18N.Repository.MetaData.AddDialog.requiredField = "This field is required.";

if(!Signavio.I18N.Repository.MetaData.Complex) { Signavio.I18N.Repository.MetaData.Complex = {}; }
Signavio.I18N.Repository.MetaData.Complex.AddAttribute = "Add column";
Signavio.I18N.Repository.MetaData.Complex.Attributes = "Columns:";
Signavio.I18N.Repository.MetaData.Complex.Name = "Name";
Signavio.I18N.Repository.MetaData.Complex.Description = "Description";
Signavio.I18N.Repository.MetaData.Complex.DataType = "Data type";
Signavio.I18N.Repository.MetaData.Complex.ColWidth = "Width";
Signavio.I18N.Repository.MetaData.Complex.ColPx = "px";
Signavio.I18N.Repository.MetaData.Complex.AsList = "As list";
Signavio.I18N.Repository.MetaData.Complex.SpecificData = "Data type specific fields";
Signavio.I18N.Repository.MetaData.Complex.Yes = "Yes";
Signavio.I18N.Repository.MetaData.Complex.No = "No";
Signavio.I18N.Repository.MetaData.Complex.NewAttribute = "New column";
Signavio.I18N.Repository.MetaData.Complex.AsList = " (as list)";
Signavio.I18N.Repository.MetaData.Complex.Remove = "Remove";
Signavio.I18N.Repository.MetaData.Complex.RemoveAttr = "Remove column";
Signavio.I18N.Repository.MetaData.Complex.RemoveAttrMsg = "Do you really want to delete this column?";
Signavio.I18N.Repository.MetaData.Complex.NoFields = "No data-type-specific fields"; 
Signavio.I18N.Repository.MetaData.Complex.NoChildAttributes = "A table must have at least one column.";
Signavio.I18N.Repository.MetaData.Complex.NoChildAttributeName = "A column must have a name.";
Signavio.I18N.Repository.MetaData.Complex.LoadingChildAttributes = "Loading columns...";
Signavio.I18N.Repository.MetaData.Complex.InvalidNames = "There cannot be two or more columns with identical names.";
Signavio.I18N.Repository.MetaData.Complex.MoveUp = "Move up";
Signavio.I18N.Repository.MetaData.Complex.MoveDown = "Move down";
Signavio.I18N.Repository.MetaData.Complex.WrongColWidth = "The column width has to be at least 1.";

if(!Signavio.I18N.Repository.MetaData.ShowAddDialog) {Signavio.I18N.Repository.MetaData.ShowAddDialog = {};}
Signavio.I18N.Repository.MetaData.ShowAddDialog.Name		= "Name";
Signavio.I18N.Repository.MetaData.ShowAddDialog.Identifier	= "Identifier";
Signavio.I18N.Repository.MetaData.ShowAddDialog.Description	= "Description";
Signavio.I18N.Repository.MetaData.ShowAddDialog.DefaultValue= "Default value";
Signavio.I18N.Repository.MetaData.ShowAddDialog.DataType	= "Data type";
Signavio.I18N.Repository.MetaData.ShowAddDialog.AsList		= "As list";
Signavio.I18N.Repository.MetaData.ShowAddDialog.LineWrap	= "Line wrap";
Signavio.I18N.Repository.MetaData.ShowAddDialog.UseOnly		= "Only for";
Signavio.I18N.Repository.MetaData.ShowAddDialog.DateFormat	= "Date format";
Signavio.I18N.Repository.MetaData.ShowAddDialog.DateFormatInfo = "Defines the format which the date is stored in, e.g. 'H:i:s d-m-Y' for hour:minute:second day-month-year.";
Signavio.I18N.Repository.MetaData.ShowAddDialog.Items		= "Items";
Signavio.I18N.Repository.MetaData.ShowAddDialog.Minimum		= "Minimum";
Signavio.I18N.Repository.MetaData.ShowAddDialog.Maximum		= "Maximum";
Signavio.I18N.Repository.MetaData.ShowAddDialog.AddNewAttribute = "Add new attribute";
Signavio.I18N.Repository.MetaData.ShowAddDialog.EditAttribute = "Edit attribute";
Signavio.I18N.Repository.MetaData.ShowAddDialog.InvalidFields = "The following field(s) need(s) a value:\n'";
Signavio.I18N.Repository.MetaData.ShowAddDialog.invalidTitle = "The custom attribute needs a name.";
Signavio.I18N.Repository.MetaData.ShowAddDialog.invalidItems = "A dropdown box needs at least one item.";
Signavio.I18N.Repository.MetaData.ShowAddDialog.invalidMinMax = "Maximum must be greater than minimum.";
Signavio.I18N.Repository.MetaData.ShowAddDialog.asMultilanguage = "Enabled for multiple languages";
Signavio.I18N.Repository.MetaData.ShowAddDialog.maxLengthText = "The maximum length for this field is {0}.";
Signavio.I18N.Repository.MetaData.ShowAddDialog.star = "Mark as default";
Signavio.I18N.Repository.MetaData.ShowAddDialog.stared = "Marked as default";

if(!Signavio.I18N.Repository.MetaData.Categories) {Signavio.I18N.Repository.MetaData.Categories = {};}
Signavio.I18N.Repository.MetaData.Categories.addNew = "Add category";
Signavio.I18N.Repository.MetaData.Categories.other = "Others";
Signavio.I18N.Repository.MetaData.Categories.edit = "Edit category";
Signavio.I18N.Repository.MetaData.Categories.addError = "The category {name} could not be added because a category with that name already exists.";
Signavio.I18N.Repository.MetaData.Categories.editError = "Changes have not been applied because a category named '{name}' already exists.";
Signavio.I18N.Repository.MetaData.Categories.remove = "Remove category";
Signavio.I18N.Repository.MetaData.Categories.removeConfirmation = "Are you sure you want to remove the selected category{0}?";
Signavio.I18N.Repository.MetaData.Categories.removeConfirmation = "You are about to remove the selected category{0}. Do you want to delete existing dictionary links in this category, or move them to '{1}'";
Signavio.I18N.Repository.MetaData.Categories.removeConfirmationSub = " and all its sub categories";
Signavio.I18N.Repository.MetaData.Categories.needsName = "A category needs a name";
Signavio.I18N.Repository.MetaData.Categories.parentCategory = "Parent category";
Signavio.I18N.Repository.MetaData.Categories.resetColor = "Reset color";
Signavio.I18N.Repository.MetaData.Categories.color = "Color";

if(!Signavio.I18N.Repository.MetaData.WarningBox) {Signavio.I18N.Repository.MetaData.WarningBox = {};}
Signavio.I18N.Repository.MetaData.WarningBox.title = "Apply changes";
Signavio.I18N.Repository.MetaData.WarningBox.description = "To apply the new changes, the website has to refresh. <br\> Refresh the page, now?";


if(!Signavio.I18N.Repository.MetaData.Subsets) {Signavio.I18N.Repository.MetaData.Subsets = {};}
Signavio.I18N.Repository.MetaData.Subsets.AddSubset = "Add new subset";
Signavio.I18N.Repository.MetaData.Subsets.EditSubset = "Edit exisiting subset";
Signavio.I18N.Repository.MetaData.Subsets.AddSubsetText = "Please define the name of the new subset.";
Signavio.I18N.Repository.MetaData.Subsets.ConfirmRemoval = "Remove subset";
Signavio.I18N.Repository.MetaData.Subsets.ConfirmRemovalText = "Do you really want to delete the subset?";
Signavio.I18N.Repository.MetaData.Subsets.Button = "Save";
Signavio.I18N.Repository.MetaData.Subsets.RemoveButton = "Remove subset";
Signavio.I18N.Repository.MetaData.Subsets.CancelButton = "Cancel";
Signavio.I18N.Repository.MetaData.Subsets.DuplicateButton = "Duplicate";
Signavio.I18N.Repository.MetaData.Subsets.SubsetName = "Name of the subset";
Signavio.I18N.Repository.MetaData.Subsets.SubsetAddDescription = "With this dialog you can define a subset of diagram element types. For this you need to provide a name and define whether the elements should be grouped into categories or not.";
Signavio.I18N.Repository.MetaData.Subsets.SubsetEditDescription = "With this dialog you can edit an existing subset of diagram element types.";
Signavio.I18N.Repository.MetaData.Subsets.SubsetDuplicateDescription = "With this dialog you can duplicate an existing subset.";
Signavio.I18N.Repository.MetaData.Subsets.SubsetGrouping = "Group diagram element types";
Signavio.I18N.Repository.MetaData.Subsets.NoNameDescription = "The subset must have a name.";


// GLOSSARY
if(!Signavio.I18N.Repository.GlossaryTypes) {Signavio.I18N.Repository.GlossaryTypes = {};}
Signavio.I18N.Repository.GlossaryTypes.DOCUMENT = "Documents";
Signavio.I18N.Repository.GlossaryTypes.ORG_UNIT = "Organizational Units";
Signavio.I18N.Repository.GlossaryTypes.IT_SYSTEM = "IT Systems";
Signavio.I18N.Repository.GlossaryTypes.ACTIVITY = "Activities";
Signavio.I18N.Repository.GlossaryTypes.STATE = "Events";

if(!Signavio.I18N.btn) {Signavio.I18N.btn = {};}
Signavio.I18N.btn.btnEdit = "Edit";
Signavio.I18N.btn.btnRemove = "Remove";
Signavio.I18N.btn.moveUp = "Move up";
Signavio.I18N.btn.moveDown = "Move down";

if(!Signavio.I18N.field) {Signavio.I18N.field = {};}
Signavio.I18N.field.Url = "URL";
Signavio.I18N.field.UrlLabel = "Label";

// SPREADSHEET-MODELING
if(!Signavio.I18N.Repository.Spreadsheet) {Signavio.I18N.Repository.Spreadsheet = {};}
Signavio.I18N.Repository.Spreadsheet.exportTitle = "Export spreadsheet (XLS)";
Signavio.I18N.Repository.Spreadsheet.exportDescription = "Transforms the process diagram into a spreadsheet representation (that can be modified and re-imported).";
Signavio.I18N.Repository.Spreadsheet.exportWinTitle = "Spreadsheet process model export";
Signavio.I18N.Repository.Spreadsheet.exportButtonExp = "Spreadsheet-based model";
Signavio.I18N.Repository.Spreadsheet.exportLabel = "Please select an export mode:";
Signavio.I18N.Repository.Spreadsheet.simpleModeTitle = "List of activities";
Signavio.I18N.Repository.Spreadsheet.simpleMode = "In the simple mode, only activities are exported into a sequentialized spreadsheet.";
Signavio.I18N.Repository.Spreadsheet.simpleModeImgTag = '<img src="/images/signavio/spreadsheet_list.png" alt="resource planning" style="border: 0px" />';
Signavio.I18N.Repository.Spreadsheet.advancedModeTitle = "Extended table";
Signavio.I18N.Repository.Spreadsheet.advancedMode = "Using the advanced mode, activities, events and control flow structures are exported.";
Signavio.I18N.Repository.Spreadsheet.advancedModeImgTag = '<img src="/images/signavio/spreadsheet_table.png" alt="resource planning" style="border: 0px" />';

Signavio.I18N.Repository.Spreadsheet.importTitle = "Import spreadsheet (XLS)";
Signavio.I18N.Repository.Spreadsheet.importDescription = "Imports a modified spreadsheet-based model back.";
Signavio.I18N.Repository.Spreadsheet.importWinTitle = "Spreadsheet merge back";
Signavio.I18N.Repository.Spreadsheet.importLabel = "Please select a spreadsheet-based process diagram you want to import back into the system.";
Signavio.I18N.Repository.Spreadsheet.importSuccess = "The spreadsheet reimport was successful.";
Signavio.I18N.Repository.Spreadsheet.importOpen = "You can open the diagram in the <a href=\"#{uri}?id=#{id}\" target=\"_blank\">editor</a> now.";
Signavio.I18N.Repository.Spreadsheet.importConflicts = "However, there were some conflicts that could not be resolved automatically:";
Signavio.I18N.Repository.Spreadsheet.importFail = "The spreadsheet reimport failed";

Signavio.I18N.Repository.Spreadsheet.addition = "Could not add <b>#{resource}</b> because #{reason}.";
Signavio.I18N.Repository.Spreadsheet.deletion = "Could not delete <b>#{resource}</b> because #{reason}.";
Signavio.I18N.Repository.Spreadsheet.addModification = "Could not add <b>#{property}</b> to <b>#{resource}</b> because #{reason}.";
Signavio.I18N.Repository.Spreadsheet.deleteModification = "Could not delete <b>#{property}</b> from <b>#{resource}</b> because #{reason}.";
Signavio.I18N.Repository.Spreadsheet.modifyModification = "Could not modify <b>#{property}</b> of <b>#{resource}</b> because #{reason}.";

Signavio.I18N.Repository.Spreadsheet.RESOURCE_DOES_NOT_EXIST_ANYMORE = "the resource does not exist anymore";
Signavio.I18N.Repository.Spreadsheet.PROPERTY_DOES_NOT_EXIST_ANYMORE = "the property does not exist anymore";
Signavio.I18N.Repository.Spreadsheet.AMBIGUOUS_CONTROLFLOW = "it is an ambiguous control flow relation";
Signavio.I18N.Repository.Spreadsheet.UNKNOWN_ERROR = "of an unknown error";
Signavio.I18N.Repository.Spreadsheet.LANE_DOES_NOT_EXIST = "the target lane does not exist";
Signavio.I18N.Repository.Spreadsheet.PROPERTY_TYPE_NOT_SUPPORTED = "the property type is not supported for this element";

if(!Signavio.I18N.Repository.Analysis) Signavio.I18N.Repository.Analysis = {};
Signavio.I18N.Repository.Analysis.titleCost = "Process cost analysis (XLS)";
Signavio.I18N.Repository.Analysis.descriptionCost = "Generates a report on costs of the selected set of process diagrams.";
Signavio.I18N.Repository.Analysis.titleRes = "Resource consumption analysis (XLS)";
Signavio.I18N.Repository.Analysis.descriptionRes = "Generates a report on resource consumptions of the selected set of process diagrams.";
Signavio.I18N.Repository.Analysis.title = "Quantitative analysis";
Signavio.I18N.Repository.Analysis.error1 = "No EPC nor BPMN 2.0 diagram selected.";
Signavio.I18N.Repository.Analysis.selectMode = "Please select a report mode:";
Signavio.I18N.Repository.Analysis.costMode = "Process cost analysis";
Signavio.I18N.Repository.Analysis.costModeDescription = "Calculation of process costs ({currency} per cost center) based on the execution frequency and costs per activity.";
Signavio.I18N.Repository.Analysis.costModeImgTag = '<img src="/images/signavio/analysis_cost_en.png" alt="cost calculation" class="plugin-purl-previewimg" />';
Signavio.I18N.Repository.Analysis.costModeButton = "Use resource consumption attributes for the calculation (if provided)";
Signavio.I18N.Repository.Analysis.timeMode = "Resource consumption analysis";
Signavio.I18N.Repository.Analysis.timeModeDescription = "Calculation of resource consumption (hours per resource) based on the execution frequency and execution time per activity.";
Signavio.I18N.Repository.Analysis.timeModeImgTag = '<img src="/images/signavio/analysis_resources_en.png" alt="resource planning" class="plugin-purl-previewimg" />';
Signavio.I18N.Repository.Analysis.includeButton = "Include subprocesses in calculation";
Signavio.I18N.Repository.Analysis.startButton = "Start Calculation";
Signavio.I18N.Repository.Analysis.nextButton = "Next >>";
Signavio.I18N.Repository.Analysis.specifyMapping = "Please specify resource costs:";
Signavio.I18N.Repository.Analysis.mappingPosition = "Resource";
Signavio.I18N.Repository.Analysis.mappingCosts = "Costs {currency} / h";
Signavio.I18N.Repository.Analysis.backButton = "<< Back";
Signavio.I18N.Repository.Analysis.quantityAnalysisTitle = "quantitative Analysis";
Signavio.I18N.Repository.Analysis.quantityAnalysisDescr = "Please provide more details on resource calculation:";
Signavio.I18N.Repository.Analysis.personalAllowanceTimeField = "Personal distribute time (%)";
Signavio.I18N.Repository.Analysis.objectAllowanceTimeField = "Technical distribute time (%)";
Signavio.I18N.Repository.Analysis.shouldWorkDaysPerYear = "Nominal value of work days / year";
Signavio.I18N.Repository.Analysis.shouldWorkHoursPerDay = "Nominal value of work hours / day";
Signavio.I18N.Repository.Analysis.saveForFuture = "Save for future calculation";
Signavio.I18N.Repository.Analysis.invalidBetweenonehundred = "The value should be beetween 0 and 100.";
Signavio.I18N.Repository.Analysis.invalidGreaterOneYear = "The value should be beetween 0 and 365.";
Signavio.I18N.Repository.Analysis.invalidGreaterOneDay = "The value should be beetween 0 and 24.";
Signavio.I18N.Repository.Analysis.decimalSeperator = ".";
Signavio.I18N.Repository.Analysis.personalAllowanceTimeFieldTT = "Proportional units of time that are required to meet human needs (value between 0 and 100).";
Signavio.I18N.Repository.Analysis.objectAllowanceTimeFieldTT = "Proportional units of time that are required to supplement organizational gaps (value between 0 and 100).";
Signavio.I18N.Repository.Analysis.shouldWorkDaysPerYearTT = "Average work days of a full-time employee per year (value between 0 and 365).";
Signavio.I18N.Repository.Analysis.shouldWorkHoursPerDayTT = "Average work hours of a full-time employee per day (value between 0 and 24).";

Signavio.I18N.Repository.Analysis.responsibilityAnalysisTitle = "Responsibility assignment matrix / RACI (XLS)";
Signavio.I18N.Repository.Analysis.responsibilityAnalysisDesc = "Generates a report where all organisations and their responsibility functions are shown.";
Signavio.I18N.Repository.Analysis.responsibilityAnalysisGenerateBtn = "Start analysis";
Signavio.I18N.Repository.Analysis.responsibilityAnalysisGenerateWaiting = "Analyzing...";

Signavio.I18N.Repository.Analysis.standardReportGenerateButton = "Start analysis";
Signavio.I18N.Repository.Analysis.standardReportGenerateGenerateWaiting = "Analyzing...";
Signavio.I18N.Repository.Analysis.docUseTitle = "Documents usage matrix (XLS)";
Signavio.I18N.Repository.Analysis.docUseDesc = "Generates a report that shows the usage of documents by activities within the selected diagrams.";
Signavio.I18N.Repository.Analysis.itSystemByDiagramTitle = "IT system usage matrix (by diagrams) (XLS)";
Signavio.I18N.Repository.Analysis.itSystemByDiagramDesc = "Generates a report that shows the usage of IT systems by activities grouped by diagrams.";
Signavio.I18N.Repository.Analysis.itSystemByRolesTitle = "IT system usage matrix (by roles) (XLS)";
Signavio.I18N.Repository.Analysis.itSystemByRolesDesc = "Generates a report that shows the usage of IT systems by activities grouped by roles.";
Signavio.I18N.Repository.Analysis.metricsTitle ="Process model metrics (XLS)";
Signavio.I18N.Repository.Analysis.metricsDesc ="Generates a Process Model Metrics report for the selected diagrams.";
Signavio.I18N.Repository.Analysis.responsibilityHandoverTitle = "Responsibility handovers matrix (XLS)";
Signavio.I18N.Repository.Analysis.responsibilityHandoverDesc ="Generates a report of all responsibility handovers within the selected diagrams.";
Signavio.I18N.Repository.Analysis.activityListTitle = "Activities list (XLS)";
Signavio.I18N.Repository.Analysis.activityListCheckboxes = "Please select those activity properties that should be shown in the report as columns.";
Signavio.I18N.Repository.Analysis.activityListStoreCB = "Store configuration on server";
Signavio.I18N.Repository.Analysis.attributesReportTitle ="Process characteristics with element details (XLS)";
Signavio.I18N.Repository.Analysis.attributesReportDesc ="Generates a report on all custom attributes within the selected diagrams.";
Signavio.I18N.Repository.Analysis.wienerTitle = "Internal control matrix (XLS)";
Signavio.I18N.Repository.Analysis.wienerDesc = "Generates a Internal control matrix based on participants' attributes for WWKS.";
Signavio.I18N.Repository.Analysis.iksreportTitle = "Risks & controls report (XLS)";
Signavio.I18N.Repository.Analysis.iksreportDesc = "Generates an internal control matrix for the selection of BPMN 2.0 models.";

if(!Signavio.I18N.Repository.Reporting) Signavio.I18N.Repository.Reporting = {};
Signavio.I18N.Repository.Reporting.title = "Reporting";

Signavio.I18N.Repository.Offer.setup = "Setup";

if(!Signavio.I18N.Repository.MetaData) Signavio.I18N.Repository.MetaData = {};
Signavio.I18N.Repository.MetaData.copySubSet = "{0} (Copy)";
Signavio.I18N.Repository.MetaData.title = "Define notations/attributes";
Signavio.I18N.Repository.MetaData.titleGuidelines = "Define modeling conventions";
Signavio.I18N.Repository.MetaData.windowTitle = "Define notations/attributes";
Signavio.I18N.Repository.MetaData.confirmRemove = "Delete attribute";
Signavio.I18N.Repository.MetaData.confirmRemoveText = "Do you really want to remove {0}?";
Signavio.I18N.Repository.MetaData.addAttributes = "Add";
Signavio.I18N.Repository.MetaData.removeAttributes = "Remove";
Signavio.I18N.Repository.MetaData.editAttributes = "Edit";
Signavio.I18N.Repository.MetaData.moveDown = "Down";
Signavio.I18N.Repository.MetaData.moveUp = "Up";
Signavio.I18N.Repository.MetaData.idErrorHint  = "There is already a custom attribute with this name, however, the type of the attribute is not supported by the glossary. Please use a different title.";
Signavio.I18N.Repository.MetaData.idErrorHint2  = "There is already a custom attribute with this name. Please use a different title.";
Signavio.I18N.Repository.MetaData.selectAll = "Check all";
Signavio.I18N.Repository.MetaData.deselectAll = "Uncheck all";
Signavio.I18N.Repository.MetaData.stencilsetsDescriptionText =  "Define in your work area which stencil sets and stencils can be used for modeling. "+
                                                                "You can create and delete custom subsets for each chart type. The default subsets can neither be changed nor deleted. "+
                                                                "In addition, you can add attributes to stencils which are available for modeling. "+
                                                                "Note: Only the common attributes are displayed if multiple stencils are selected. ";
Signavio.I18N.Repository.MetaData.glossaryDescriptionText =		"You can add custom attributes to each dictionary category. " +
																"Note: Only the common attributes are displayed if multiple stencils are selected. ";
Signavio.I18N.Repository.MetaData.stencilset = 'Modeling language';
Signavio.I18N.Repository.MetaData.closeBtn = 'Close';
Signavio.I18N.Repository.MetaData.addSubset = 'Add subset';
Signavio.I18N.Repository.MetaData.copySubset = 'Copy subset';
Signavio.I18N.Repository.MetaData.removeSubset = 'Remove subset';
Signavio.I18N.Repository.MetaData.editSubset = 'Edit subset';
Signavio.I18N.Repository.MetaData.requestStencilsetSelection = 'Please select a modeling language or a subset';
Signavio.I18N.Repository.MetaData.stencil = 'Diagram element types';
Signavio.I18N.Repository.MetaData.requestStencilSelection = 'Please select an element type';
Signavio.I18N.Repository.MetaData.ownAttributes = 'Custom attributes';
Signavio.I18N.Repository.MetaData.loading = 'Modeling elements are being loaded.';

Signavio.I18N.Repository.MetaData.manageBubbleTitle = "Custom attributes";
Signavio.I18N.Repository.MetaData.manageBubbleDesc = "Choose how custom attributes will be displayed in an elements information speech bubble.";
Signavio.I18N.Repository.MetaData.manageCollapsedExpandable = "Collapsed - expandable";
Signavio.I18N.Repository.MetaData.manageExpandedCollapsible = "Expanded - collapsible";
Signavio.I18N.Repository.MetaData.managecollapsedLocked = "Hidden";
Signavio.I18N.Repository.MetaData.manageexpandedLocked = "Always expanded";

Signavio.I18N.Repository.MetaData.categories = 'Categories';
Signavio.I18N.Repository.MetaData.requestCategorySelection = 'Please select a category';
Signavio.I18N.Repository.MetaData.noOwnAttributes = 'No custom attributes are defined';
Signavio.I18N.Repository.MetaData.noSharedAttributes = 'No common attributes are defined';

Signavio.I18N.Repository.MetaData.removeAttributesText = "Do you want to completely remove '{0}' or only from the{1}?";
Signavio.I18N.Repository.MetaData.removeFromAll = 'Completely remove';
Signavio.I18N.Repository.MetaData.cancelRemove = 'Cancel';

Signavio.I18N.Repository.MetaData.commitMetaDate = "Create";
Signavio.I18N.Repository.MetaData.editMetaDate = "Edit";

Signavio.I18N.Repository.MetaData.warnTitle = "Edit attribute";
Signavio.I18N.Repository.MetaData.warnNumber = "You decreased the range, this can lead into incorrect diagrams or dictionary entries. Do you want to continue?";
Signavio.I18N.Repository.MetaData.warnGlossaryLink = "You changed the necessary type of linked dictionary entries, this can lead into incorrect diagrams or dictionary entries. Do you want to continue?";
Signavio.I18N.Repository.MetaData.warnEnum = "You removed {0} element(s) from the list of the drop down field, this can lead into incorrect diagrams or dictionary entries. Do you want to continue?";

if(!Signavio.I18N.Repository.MetaData.removeFrom) Signavio.I18N.Repository.MetaData.removeFrom = {};
Signavio.I18N.Repository.MetaData.removeFrom["singleStencil"] = " selected element type";
Signavio.I18N.Repository.MetaData.removeFrom["moreStencil"] = " selected element types";
Signavio.I18N.Repository.MetaData.removeFrom["singleCategory"] = " selected dictionary category";
Signavio.I18N.Repository.MetaData.removeFrom["moreCategory"] = " selected dictionary categories";

if(!Signavio.I18N.Repository.MetaData.removeBtn) Signavio.I18N.Repository.MetaData.removeBtn = {};
Signavio.I18N.Repository.MetaData.removeBtn["singleStencil"] = 'Remove only from element type';
Signavio.I18N.Repository.MetaData.removeBtn["moreStencil"] = 'Remove only from element types';
Signavio.I18N.Repository.MetaData.removeBtn["singleCategory"] = 'Remove only from category';
Signavio.I18N.Repository.MetaData.removeBtn["moreCategory"] = 'Remove only from categories';


if(!Signavio.I18N.Repository.MetaData.Template) Signavio.I18N.Repository.MetaData.Template = {};
Signavio.I18N.Repository.MetaData.Template.name = "Name:";
Signavio.I18N.Repository.MetaData.Template.type = "Type:";
Signavio.I18N.Repository.MetaData.Template.description = "Description:";
Signavio.I18N.Repository.MetaData.Template.items = "Items:";
Signavio.I18N.Repository.MetaData.Template.glossary = "Dictionary";
Signavio.I18N.Repository.MetaData.Template.bindings = "Used with:";
Signavio.I18N.Repository.MetaData.Template.dateFormat = "Dateformat:";
Signavio.I18N.Repository.MetaData.Template.defaultValue = "Default:";
Signavio.I18N.Repository.MetaData.Template.isList = "(as a list)";
Signavio.I18N.Repository.MetaData.Template.lineWrap = "Multilined";
Signavio.I18N.Repository.MetaData.Template.minNumber = "Minimum:";
Signavio.I18N.Repository.MetaData.Template.maxNumber = "Maximum:";
Signavio.I18N.Repository.MetaData.Template.furtherBindings = " and {0} other element types of other modeling languages.";
Signavio.I18N.Repository.MetaData.Template.furtherBindingsGlossary = " and {0} element types.";
Signavio.I18N.Repository.MetaData.Template.multilanguage = "Can be use in multiple languages.";

Signavio.I18N.Repository.Offer.exportItem = "Export {1}";
Signavio.I18N.Repository.Offer.exportItemPNG = "Export PNG (pixel graphics)";
Signavio.I18N.Repository.Offer.exportItemPNGDescription = "Show as pixel graphics";
Signavio.I18N.Repository.Offer.exportItemSVG = "Export SVG (vector graphics)";
Signavio.I18N.Repository.Offer.exportItemSVGDescription = "Show as vector graphics";
Signavio.I18N.Repository.Offer.exportItemYAWLDescription = "Transform to XML";
Signavio.I18N.Repository.Offer.exportItemXML = "Export XML";
Signavio.I18N.Repository.Offer.exportItemXMLDescription = "Transform to XML";

// BPMN 2.0 Export
Signavio.I18N.Repository.Offer.exportItemBPMN2 = "Export BPMN 2.0 XML";
Signavio.I18N.Repository.Offer.GenericExportDescription = "This diagram will be exported as #{type}. You can now choose additional options for the export.";
Signavio.I18N.Repository.Offer.exportItemBPMN2Description = "Transform to BPMN 2.0 XML";
Signavio.I18N.Repository.Offer.exportItemBPMN2Description = "Transform to BPMN 2.0 XML";
Signavio.I18N.Repository.Offer.includeLinkedProcesses = "Would you like to include linked subprocesses in the BPMN 2.0 XML?";
Signavio.I18N.Repository.Offer.includeLinkedProcessesShort = "Include linked subprocesses";

Signavio.I18N.Repository.Offer.exportType = {
    "bpmn2_0_xml": "BPMN 2.0 XML",
    "png": "PNG",
    "svg": "SVG",
    "jpdl4": "JPDL",
    "jpdl4zip": "Felten",
    "xpdlexport": "XPDL",
    "aml": "AML",
    "itsm_plausibility": "ITSM",
    "bpmn2_0_xpdl": "BPMN 2.0 XPDL",
    "rdf": "RDF",
    "timjpdl3": "T!M JPDL"
};

Signavio.I18N.Repository.Offer.exportItemBPMN2XPDL3 = "Export XPDL 2.1";
Signavio.I18N.Repository.Offer.exportItemBPMN2XPDL3Description = "Transform to XPDL 2.1";
Signavio.I18N.Repository.Offer.exportItemTIM = "Export T!M (jPDL 3)";
Signavio.I18N.Repository.Offer.exportItemTIMDescription = "Transform to T!M (jPDL 3)";
Signavio.I18N.Repository.Offer.exportItemNPB = "Export NPB XML";
Signavio.I18N.Repository.Offer.exportItemNPBDescription = "Transform to NPB XML";

Signavio.I18N.Repository.Offer.WindowDescription = "There are several stakeholder specific views available for this diagram. The original view has been pre selected for you. If you like to export another one, please select it at the bottom.";
Signavio.I18N.Repository.Offer.WindowBtnExport = "Export";
Signavio.I18N.Repository.Offer.WindowBtnCancel = "Cancel";
Signavio.I18N.Repository.Offer.noViewSelected = "You haven't chosen a view. In order to export the diagram, please select a view.";


if(!Signavio.I18N.Repository.SearchFields) Signavio.I18N.Repository.SearchFields = {};
Signavio.I18N.Repository.SearchFields.labels = "Attributes";
Signavio.I18N.Repository.SearchFields.text1 = "Revision comment";
Signavio.I18N.Repository.SearchFields.text2 = "Attributes";
Signavio.I18N.Repository.SearchFields.comments = "Comments";
Signavio.I18N.Repository.SearchFields.description = "Description";
Signavio.I18N.Repository.SearchFields.name = "Name";

//Socialize
if(!Signavio.I18N.Repository.Socialize) {Signavio.I18N.Repository.Socialize = {};}
Signavio.I18N.Repository.Socialize.title = "Discuss on BPMN.info";
Signavio.I18N.Repository.Socialize.infoMessage = "Discuss this process diagram with experts on <a href='http://www.bpmn.info' target='_blank'>BPMN.info</a>. <a href='http://www.bpmn.info' target='_blank'>BPMN.info</a> is a network for BPMN 2.0 users to share latest topics in the field of process management.";
Signavio.I18N.Repository.Socialize.warningMessage = "<b>Attention!</b><br/> A copy of the selected diagram will be publicly available on <a href='http://www.bpmn.info' target='_blank'>BPMN.info</a>.";
Signavio.I18N.Repository.Socialize.registrationMessage = "Please use your user name and password from <a href='http://test.bpmn.info' target='_blank'>BPMN.info</a>. If you don't have a user name please register <a href='http://test.bpmn.info' target='_blank'>here</a>.";
Signavio.I18N.Repository.Socialize.description = "Publish your BPMN2.0 diagram on bpmn.info.";
Signavio.I18N.Repository.Socialize.suc = "Your diagram has been published on bpmn.info.";
Signavio.I18N.Repository.Socialize.fail = "The user could not be logged in. Please use a valid user/password combination.";
Signavio.I18N.Repository.Socialize.deploy = "Discuss";
Signavio.I18N.Repository.Socialize.anonymize = "Anonymize the diagram by removing all labels.";

Signavio.I18N.Repository.Offer.exportFeltenTitle = "Export to PILOT archive";
Signavio.I18N.Repository.Offer.exportFeltenDescription = "Offers a ZIP archive of a jPDL 4 that can be imported into a PILOT system.";

//BPMN 2.0 Import
if(!Signavio.I18N.Repository.Offer.BPMN2_0Import) Signavio.I18N.Repository.Offer.BPMN2_0Import = {};
Signavio.I18N.Repository.Offer.BPMN2_0Import.title = "Import BPMN 2.0 XML";
Signavio.I18N.Repository.Offer.BPMN2_0Import.description = "Import BPMN 2.0 diagrams from .bpmn files.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.restorelabel = "Please specify a file to import. Besides the BPMN 2.0 XML Format, the process logic and diagramm interchange parts are required. The maximum file size is limited to 10 MB.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.restore = "Import BPMN 2.0 diagrams from .bpmn files.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.restoreBtn = "Import";
Signavio.I18N.Repository.Offer.BPMN2_0Import.wait = "Please wait for the BPMN 2.0 XML file to be uploaded.<br/> This may take a while...";
Signavio.I18N.Repository.Offer.BPMN2_0Import.AsyncInfoTitle = "The import has been started successfully.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.AsyncInfoDesc = "The import is processed automatically in the background and may take a while. During the import, you can use the system as usual.";

Signavio.I18N.Repository.Offer.BPMN2_0Import.LinkListDescription = "There are multiple processes contained in the BPMN 2.0 XML file. They are accessible over the list of links below. CAUTION: Each process must be opened and saved to be stored permanently in the current directory.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.LinkListProcess = "Process";

Signavio.I18N.Repository.Offer.BPMN2_0Import.ValidationFailedTitle = "The BPMN 2.0 XML file could not be validated.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.ValidationFailedDesc = "The BPMN 2.0 XML file could not be imported. Please contact our <a href='mailto:[supportMailAdresse]'>support</a> in order to solve the problem.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.NoFileTitle = "No File";
Signavio.I18N.Repository.Offer.BPMN2_0Import.NoFileDesc = "Please specify a file to send.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.FileTooBigTitle = "File too big.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.FileTooBigDesc = "The file is too big for processing.  The file size is limited to 10 MB.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.ImportStartedSuccessfullyTitle = "Import successfully started";
Signavio.I18N.Repository.Offer.BPMN2_0Import.ImportStartedSuccessfullyDesc = "The import of your BPMN 2.0 XML file has been started successfully.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.CouldNotWriteToResponseTitle = "Response write failed";
Signavio.I18N.Repository.Offer.BPMN2_0Import.CouldNotWriteToResponseDesc = "An exception occurred when writing the response. Please try again or contact our <a href='mailto:[supportMailAdresse]'>support</a> in order to solve the problem..";
Signavio.I18N.Repository.Offer.BPMN2_0Import.JSONExceptionWhenWritingJsonArrayToResponseTitle = "JSON write failed";
Signavio.I18N.Repository.Offer.BPMN2_0Import.JSONExceptionWhenWritingJsonArrayToResponseDesc = "The result (in JSON format) could not be written. Please try again or contact our <a href='mailto:[supportMailAdresse]'>support</a> in order to solve the problem.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.ReadingFileFailedTitle = "Failure reading file";
Signavio.I18N.Repository.Offer.BPMN2_0Import.ReadingFileFailedDesc = "The uploaded file could not be parsed as BPMN 2.0 XML. Please double check the appriate file type and BPMN 2.0 XML structure or contact our <a href='mailto:[supportMailAdresse]'>support</a> in order to solve the problem.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.CreatingUnmarshallerFailedTitle = "";
Signavio.I18N.Repository.Offer.BPMN2_0Import.CreatingUnmarshallerFailedDesc = "No Unmarshaller could be created. Please try again or contact our <a href='mailto:[supportMailAdresse]'>support</a> in order to solve the problem.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.ConversionFailureTitle = "Conversion failed";
Signavio.I18N.Repository.Offer.BPMN2_0Import.ConversionFailureDesc = "An exception occurred during conversion. Please contact our <a href='mailto:[supportMailAdresse]'>support</a> in order to solve the problem.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.ConversionResultCouldNotBeParsedToJsonTitle = "Result not convertible";
Signavio.I18N.Repository.Offer.BPMN2_0Import.ConversionResultCouldNotBeParsedToJsonDesc = "The result could not be convented to the internal format. Please contact our <a href='mailto:[supportMailAdresse]'>support</a> in order to solve the problem.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.CouldNotReadRequest = "The file could not be read. Please try again or contact our <a href='mailto:[supportMailAdresse]'>support</a> in order to solve the problem.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.CouldNotGetFileItemInputStream = "The file could not be read. Please try again or contact our <a href='mailto:[supportMailAdresse]'>support</a> in order to solve the problem.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.CouldNotStoreJson = "Method storeJSON failed. Please try again or contact our <a href='mailto:[supportMailAdresse]'>support</a> in order to solve the problem.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.WrongLicense = "The import of BPMN 2.0 XML files is only available in the Professional and Corporate Edition. For further information about the Basic, Professional, and Corporate Edition please follow <a href='http://signavio.com/en/produkte/process-editor-as-a-service.html' target='_blank'>this</a> link.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.NotInTrial = "The import of BPMN 2.0 XML files is not available in the free trial phase by default. Please contact us at <a href='mailto:[supportMailAdresse]'>[supportMailAdresse]</a> and we will enable this functionality for you without extra cost for your trial period.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.CouldNotGenerateSvgForDiagramTitle = "Creating the graphical representation failed";
Signavio.I18N.Repository.Offer.BPMN2_0Import.CouldNotGenerateSvgForDiagram = "Creating the graphical representation failed. Please send your BPMN 2.0 XML file to the Signavio <a href='mailto:[supportMailAdresse]'>support</a>.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.CouldNotBuildDiagramObjectFromJSON = "Creating the graphical representation failed, because the diagram object could not be built correctly. Please send your BPMN 2.0 XML file to the Signavio <a href='mailto:[supportMailAdresse]'>support</a>.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.MissingBPMNDIPartTitle = "Missing BPMN DI in BPMN 2.0 XML";
Signavio.I18N.Repository.Offer.BPMN2_0Import.MissingBPMNDIPart = "The import of the BPMN 2.0 XML failed due to missing elements in the BPMN DI part of the BPMN 2.0 XML structure. No information about the graphical elements are provided.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.NonFiniteNumbers = "The given file contains invalid position values ('NaN', 'INF' or '-INF'). Please contact the creator of the file. To avoid this error, you can manually replace all occurrences of the invalid values with valid ones.";

Signavio.I18N.Repository.Offer.BPMN2_0Import.ReadingFileFailedTitle = "Failure reading file";
Signavio.I18N.Repository.Offer.BPMN2_0Import.ReadingFileFailedDesc = "The uploaded file could not be processed as a BPMN 2.0 XML file. Please double check the file type and BPMN 2.0 XML structure or contact the <a href='mailto:[supportMailAdresse]'>Signavio support</a> in order to solve the problem. Please include the applicable file in the support request, if possible.";

Signavio.I18N.Repository.Offer.BPMN2_0Import.GeneralErrorTitle = "Failure during the import";
Signavio.I18N.Repository.Offer.BPMN2_0Import.GeneralErrorDesc = "The uploaded file could not be processed as a BPMN 2.0 XML file. Please try it again later or contact the <a href='mailto:[supportMailAdresse]'>Signavio support</a> in order to solve the problem. Please include the applicable file in the support request, if possible.";

Signavio.I18N.Repository.Offer.BPMN2_0Import.WarningDesc = "The import process of the BPMN 2.0 XML file could not be completed successfully. Please check out the error and warning messages in advance or contact the <a href='mailto:[supportMailAdresse]'>Signavio support</a> in order to solve the problem. Please include the applicable file in the support request, if possible.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.ErrorDesc = "The import process of the BPMN 2.0 XML file failed. Please check out the error and warning messages in advance or contact the <a href='mailto:[supportMailAdresse]'>Signavio support</a> in order to solve the problem. Please include the applicable file in the support request, if possible.";

/* BPMN 2.0 Import Warning and Error Messages */
Signavio.I18N.Repository.Offer.BPMN2_0Import.TitleWarnings = "Warnings";
Signavio.I18N.Repository.Offer.BPMN2_0Import.TitleErrors = "Errors";
Signavio.I18N.Repository.Offer.BPMN2_0Import.HeaderTypeWarnings = "Warning Type";
Signavio.I18N.Repository.Offer.BPMN2_0Import.HeaderTypeErrors = "Error Type";
Signavio.I18N.Repository.Offer.BPMN2_0Import.HeaderDetailsWarnings = "Warning Details";
Signavio.I18N.Repository.Offer.BPMN2_0Import.HeaderDetailsErrors = "Error Details";
Signavio.I18N.Repository.Offer.BPMN2_0Import.OK = "OK";
Signavio.I18N.Repository.Offer.BPMN2_0Import.DuplicatedId = "Duplicate ID";
Signavio.I18N.Repository.Offer.BPMN2_0Import.DuplicatedIdDetail = "Duplicate ID (#{shapeid}) at Element: #{shapeclass}";
Signavio.I18N.Repository.Offer.BPMN2_0Import.MissingBPMNDIPart = "Missing BPMN DI";
Signavio.I18N.Repository.Offer.BPMN2_0Import.MissingBPMNDIPartDetail = "The diagram interchange part of BPMN 2.0 definitions is missing.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.MissingBPMNDIElement = "Missing BPMN DI Element";
Signavio.I18N.Repository.Offer.BPMN2_0Import.MissingBPMNDIElementDetail = "The following shape was discarded, because no graphical information (DI element) could be found: {shapeclass} ({shapeid})";
Signavio.I18N.Repository.Offer.BPMN2_0Import.DiscardedElement = "Discarded Element";
Signavio.I18N.Repository.Offer.BPMN2_0Import.DiscardedElementDetail = "Discarded element due to invalid reference from DI to Semantics part in BPMN 2.0 XML: {shapeclass} ({shapeid})";
Signavio.I18N.Repository.Offer.BPMN2_0Import.MissingWaypoint = "Missing Edge Waypoint";
Signavio.I18N.Repository.Offer.BPMN2_0Import.MissingWaypointDetail = "The Edge ({edgeId}) has missing waypoints defining the edge's path. The BPMN 2.0 specification requires a minimum of two waypoints.";
Signavio.I18N.Repository.Offer.BPMN2_0Import.InvalidPositionValue = "Invalid position value";
Signavio.I18N.Repository.Offer.BPMN2_0Import.InvalidPositionValueDetail = "The element with ID ({shapeId}) contains the invalid positioning value: {value}";


Signavio.I18N.Repository.Offer.BPMN2_0Import.SchemaInvalidId = "Schema invalid ID";
Signavio.I18N.Repository.Offer.BPMN2_0Import.SchemaInvalidIdDetail = "The ID ({shapeid}) @ {shapeclass} is invalid due to the type XSD:ID and was replaced.";

Signavio.I18N.Repository.Offer.BPMN2_0Import.DuplicatedProcessId = "Duplicate ProcessID";
Signavio.I18N.Repository.Offer.BPMN2_0Import.DuplicatedIdDetail = "There was a duplicate ShapeID ({shapeid}) found. If applicable please check the attribute ShapeID.";

if (!Signavio.I18N.Repository.NoBrowserSupport) Signavio.I18N.Repository.NoBrowserSupport = {};
Signavio.I18N.Repository.NoBrowserSupport.Title = "Obsolete browser version";
Signavio.I18N.Repository.NoBrowserSupport.Description = "You are currently using a browser with a version not supported by Signavio. Please upgrade your browser or download our free alternative <a target='_blank' href='http://www.signavio.com/de/browserkompatibilitaet.html'>Signavio Thin Client</a>. More information about the browser compatibility can be found <a target='_blank' href='http://www.signavio.com/de/browserkompatibilitaet.html'>here</a> or contact our <a href='mailto:[supportMailAdresse]'>Support</a>.";
Signavio.I18N.Repository.NoBrowserSupport.ie9Compatibility = "You are using the <a target='_blank' href='http://windows.microsoft.com/en-GB/internet-explorer/products/ie-9/features/compatibility-view'>compatibility view</a> of the Internet Explorer 9. When using the compatibility view, modelling diagrams is not supported. Please deactivate the compatibility view and try again.";

if(!Signavio.I18N.BPMN2_0Import) Signavio.I18N.BPMN2_0Import= {};
if(!Signavio.I18N.BPMN2_0Import.dialog) Signavio.I18N.BPMN2_0Import.dialog = {};
Signavio.I18N.BPMN2_0Import.dialog.title = "Import BPMN 2.0";
Signavio.I18N.BPMN2_0Import.dialog.ok = "Import";
Signavio.I18N.BPMN2_0Import.dialog.wait = "This may take a while...";
Signavio.I18N.BPMN2_0Import.dialog.failed = "Failure while loading the file.";
Signavio.I18N.BPMN2_0Import.dialog.close = "Cancel";

// ITSM BPMN 2.0 Export
if (!Signavio.I18N.Repository.Offer.ITSM_EXPORT) Signavio.I18N.Repository.Offer.ITSM_EXPORT = {};
Signavio.I18N.Repository.Offer.ITSM_EXPORT.Title = "Export BPMN 2.0 XML for ITSM";
Signavio.I18N.Repository.Offer.ITSM_EXPORT.Description = "Transform to BPMN 2.0 XML for ITSM";
Signavio.I18N.Repository.Offer.ITSM_EXPORT.NoExportTitle = "Export not possible";
Signavio.I18N.Repository.Offer.ITSM_EXPORT.NoITSMProcessInfo = "The selected process is not an ITSM process.";
Signavio.I18N.Repository.Offer.ITSM_EXPORT.ErrorsInProcessInfo = "This process cannot be exported because it contains plausibility errors. Please open the process in the Editor and run the plausibility check for more information.";
Signavio.I18N.Repository.Offer.ITSM_EXPORT.OpenLink = "Open Editor";

//NPB XML Import
if (!Signavio.I18N.Repository.Offer.NPBImport) Signavio.I18N.Repository.Offer.NPBImport = {};
Signavio.I18N.Repository.Offer.NPBImport.title = "Import NPB XML";
Signavio.I18N.Repository.Offer.NPBImport.description = "Import a diagram from the National Process Library (NPB).";
Signavio.I18N.Repository.Offer.NPBImport.restorelabel = "Please specify a file to import. The file has to have the NPB XML format, which can be obtained by directly exporting it from the National Process Library. The maximum file size is limited to 10 MB. If the file contains a Signavio diagram, it can be imported directly. Otherwise it is possible to import the contained image.";
Signavio.I18N.Repository.Offer.NPBImport.restore = "Import diagram from NPB XML file";
Signavio.I18N.Repository.Offer.NPBImport.restoreBtn = "Import";
Signavio.I18N.Repository.Offer.NPBImport.wait = "Please wait for the NPB XML file to be uploaded.<br/>This may take a while...";
Signavio.I18N.Repository.Offer.NPBImport.choiceFile = "Import the process as a Signavio diagram.";
Signavio.I18N.Repository.Offer.NPBImport.choiceImage = "Import the process as an image.";

//NPB XML Import Errors and Warnings
Signavio.I18N.Repository.Offer.NPBImport.NoFileTitle = "No File";
Signavio.I18N.Repository.Offer.NPBImport.NoFileDesc = "Please specify a file to send.";
Signavio.I18N.Repository.Offer.NPBImport.FileTooBigTitle = "File too big.";
Signavio.I18N.Repository.Offer.NPBImport.FileTooBigDesc = "The file is too big for processing.  The file size is limited to 10 MB.";
Signavio.I18N.Repository.Offer.NPBImport.GeneralErrorTitle = "Failure during the import";
Signavio.I18N.Repository.Offer.NPBImport.GeneralErrorDesc = "The uploaded file could not be processed as a NPB XML file. Please try it again later or contact the <a href='mailto:[supportMailAdresse]'>Signavio support</a> in order to solve the problem. Please include the applicable file in the support request, if possible.";
Signavio.I18N.Repository.Offer.NPBImport.CouldNotGenerateSvgForDiagramTitle = "Creating the graphical representation failed";
Signavio.I18N.Repository.Offer.NPBImport.CouldNotGenerateSvgForDiagramDesc = "Creating the graphical representation failed. Please send your NPB XML file to the Signavio <a href='mailto:[supportMailAdresse]'>support</a>.";
Signavio.I18N.Repository.Offer.NPBImport.UnsupportedStencilsetTitle = "Diagram Type not supported";
Signavio.I18N.Repository.Offer.NPBImport.UnsupportedStencilsetDesc = "This diagram type cannot be used in combination with NPB extension.";
Signavio.I18N.Repository.Offer.NPBImport.UnsupportedFileFormatTitle = "Unknow Diagram Type";
Signavio.I18N.Repository.Offer.NPBImport.UnsupportedFileFormatDesc = "The diagram contained in the specified file is not a Signavio diagram and cannot be imported.";
Signavio.I18N.Repository.Offer.NPBImport.UnsupportedImageFormatTitle = "Unknown Image Format";
Signavio.I18N.Repository.Offer.NPBImport.UnsupportedImageFormatDesc = "The image contained in the specified file has an unknown format and cannot be imported.";
Signavio.I18N.Repository.Offer.NPBImport.ContainsNoImageFileTitle = "No Image contained";
Signavio.I18N.Repository.Offer.NPBImport.ContainsNoImageFileDesc = "The specified file contains no image to be imported.";
Signavio.I18N.Repository.Offer.NPBImport.FileQuotaExceededTitle = "File Quota Exceeded";
Signavio.I18N.Repository.Offer.NPBImport.FileQuotaExceededDesc = "The image cannot be imported due to a lack of sufficient space.";
Signavio.I18N.Repository.Offer.NPBImport.NothingToImportTitle = "Import failed";
Signavio.I18N.Repository.Offer.NPBImport.NothingToImportDesc = "No compatible diagram or image could be imported.";
Signavio.I18N.Repository.Offer.NPBImport.ModelImportSuccessfulTitle = "Diagram imported";
Signavio.I18N.Repository.Offer.NPBImport.ModelImportSuccessfulDesc = "The diagram was imported successfully.";
Signavio.I18N.Repository.Offer.NPBImport.ImageImportSuccessfulTitle = "Image imported";
Signavio.I18N.Repository.Offer.NPBImport.ImageImportSuccessfulDesc = "No compatible diagram could be found. Instead the embedded image was imported.";

if (!Signavio.I18N.Repository.Offer.Astah) Signavio.I18N.Repository.Offer.Astah = {};
Signavio.I18N.Repository.Offer.Astah.importTitle = "Import Astah";
Signavio.I18N.Repository.Offer.Astah.importDescription = "Import Astah files (.asta)";
Signavio.I18N.Repository.Offer.Astah.importInfoText = "The following diagram types can be imported from an Astah file (.asta):<ul><li>Activity diagrams (are transformed to BPMN 2.0 diagrams)</li><li>Process maps</li><li>UML Use Case diagrams</li><li>UML Class diagrams</li></ul>If you import a file again, already existing diagrams will be updated (a new revision is created). You can deactivate this behavior in the extended options.";
Signavio.I18N.Repository.Offer.Astah.importHintPrivateDir = "Please note: You are trying to import an Astah file into a private directory. Astah diagrams that have been imported into private directories cannot be updated.";
Signavio.I18N.Repository.Offer.Astah.importExistingShow = "Show older imports";
Signavio.I18N.Repository.Offer.Astah.importExistingHide = "Hide older imports";
Signavio.I18N.Repository.Offer.Astah.importExistingInfo = "The following list shows folders, which contain already imported Astah diagrams that can be updated. You can click on \'Deactivate updates\' to prevent any future updates.";
Signavio.I18N.Repository.Offer.Astah.importExistingDelete = "Deactivate updates";
Signavio.I18N.Repository.Offer.Astah.importExistingDeleteConfirm = "Do you really want to deactivate updates for the selected folder and all its contained diagrams?";
Signavio.I18N.Repository.Offer.Astah.importFormInfoText = "Please select your .asta file and click on Import.";
Signavio.I18N.Repository.Offer.Astah.importExtendedFormInfoText = "If you check the option \'Enable updates\', you can import an updated version of the Astah file at a later point in time. When updating, the option has to be checked as well. If any problems occur during the update the addtional option \'Fresh Import\' can be activated to do a complete fresh import of the whole file and to enable future updates for this import.";
Signavio.I18N.Repository.Offer.Astah.importEnableTracking = "Enable updates";
Signavio.I18N.Repository.Offer.Astah.importForceRemapping = "Fresh Import (only activate in problematic case)";
Signavio.I18N.Repository.Offer.Astah.importOptionsShow = "Show extended options";
Signavio.I18N.Repository.Offer.Astah.importOptionsHide = "Hide extended options";
Signavio.I18N.Repository.Offer.Astah.importWaitMsg = "Importing Astah file. This may take several minutes.";
Signavio.I18N.Repository.Offer.Astah.importBtn = "Import";
Signavio.I18N.Repository.Offer.Astah.importErrorTitle = "Import failed";
Signavio.I18N.Repository.Offer.Astah.importCommunicationError = "The communication with the server failed.";
Signavio.I18N.Repository.Offer.Astah.importGeneralError = "An error occured while importing the uploaded file. Please, try again and contact the <a href='mailto:[supportMailAdresse]'>support</a> if the error occurs again. It is helpful if you attach the respective Astah file to your support request.";
Signavio.I18N.Repository.Offer.Astah.importDefaultErrorMessage = "An unknown error has occurred.";
Signavio.I18N.Repository.Offer.Astah.importMessageSuccessInitialWithInfo = "The diagrams contained in the Astah file could only be imported partially. Please see the following messages for further information:";
Signavio.I18N.Repository.Offer.Astah.importMessageSuccessInitialWithoutInfo = "The diagrams contained in the Astah file have been imported successfully.";
Signavio.I18N.Repository.Offer.Astah.importMessageSuccessFollowingWithInfo = "The diagrams contained in the Astah file did already exist in your workspace and could only be updated partially. Please see the following messages for further information:";
Signavio.I18N.Repository.Offer.Astah.importMessageSuccessFollowingWithoutInfo = "The diagrams contained in the Astah file did already exist in your workspace and have been updated successfully.";
Signavio.I18N.Repository.Offer.Astah.importTakesTooLong = "The import will take several more minutes. You will receive an email with the results of the import.";
if (!Signavio.I18N.Repository.Offer.Astah.importMessageHeader) Signavio.I18N.Repository.Offer.Astah.importMessageHeader = {};
Signavio.I18N.Repository.Offer.Astah.importMessageHeader.Infos = "Info";
Signavio.I18N.Repository.Offer.Astah.importMessageHeader.Warnings = "Warnings";
Signavio.I18N.Repository.Offer.Astah.importMessageHeader.Errors = "Errors";
Signavio.I18N.Repository.Offer.Astah.importMessageDetails = "Details";
Signavio.I18N.Repository.Offer.Astah.importMessageBtnOk = "Ok";

//Views
if(!Signavio.I18N.Views) Signavio.I18N.Views = {};
Signavio.I18N.Views.view = "View";
Signavio.I18N.Views.choosePredefiniedView = "Choice of predefined views: ";
Signavio.I18N.Views.descriptionNoPredefiniedView = 'There are no views available for this diagram. To create a simplified view, change to the tab "Create simplified view". The Editor allows creating more detailed view using the attribute editor on the right.  <br><br> Further information can be found in our <a target="_blank" rel="external" href="https://editor.signavio.com/help/en/index.html?inviting_colleagues_to_comment.htm">Help</a>.';
Signavio.I18N.Views.descriptionPredefiniedView = "Select a view from the drop-down field.";
Signavio.I18N.Views.descriptionCreateView = "Create a simplified view by hiding role assignments, IT systems, data objects, and/or comments. ";
Signavio.I18N.Views.comments = "Comments";
Signavio.I18N.Views.roles = "Role assignments";
Signavio.I18N.Views.iTSystems = "IT systems";
Signavio.I18N.Views.dataObjects = "Data objects";
Signavio.I18N.Views.selectionCriteria = "Diagram elements to display:";
Signavio.I18N.Views.createViews = "Create simplified view";
Signavio.I18N.Views.predefinedViews = "Selection of a stakeholder specific view";
Signavio.I18N.Views.titleViews = "Selection of a simplified view";
Signavio.I18N.Views.oK = "Choose view";
Signavio.I18N.Views.cancel = "Cancel";
Signavio.I18N.Views.preview = "Preview";
Signavio.I18N.Views.loadPreview = "Preview is loading...";
Signavio.I18N.Views.infoButtonDescription = 'You can define a simplified view for the diagram and present this view to your colleagues to comment on. For example, you can hide roles or IT systems.';
Signavio.I18N.Views.infoButtonDescriptionDisable = "Simplified views are only available for BPMN 2.0 process diagrams.";
Signavio.I18N.Views.simplifiedViewsHint = '<div style="padding-top:5px; font-size:11px;"><a href="#" class="x-simplified-view">Selection of a simplified view</a></div>';
Signavio.I18N.Views.simplifiedViewsHintDisable = '<div style="padding-top:5px; font-size:11px; color: rgb(151,151,151); font-style:italic;"><u>Selection of a simplified view</u></div>';
Signavio.I18N.Views.noViewSelected = "(There is no selected view)";
Signavio.I18N.Views.viewSelected ="Selected view: ";
Signavio.I18N.Views.adhocViewSelected = "Simplified view with hidden diagram elements: ";


//ADONIS Import
if(!Signavio.I18N.Repository.Offer.Adonis) Signavio.I18N.Repository.Offer.Adonis = {};
Signavio.I18N.Repository.Offer.Adonis.title = "ADONIS Import";
Signavio.I18N.Repository.Offer.Adonis.description = "Import of ADONIS BPMS models";
Signavio.I18N.Repository.Offer.Adonis.restorelabel = "Please specify a file to import. The file format ADONIS XML is required. The maximum file size is limited to 10 MB.";
Signavio.I18N.Repository.Offer.Adonis.restore = "Import of ADONIS BPMS models";
Signavio.I18N.Repository.Offer.Adonis.restoreBtn = "Import";
Signavio.I18N.Repository.Offer.Adonis.wait = "Please wait while the ADONIS XML is being uploaded. This will take a moment...";
Signavio.I18N.Repository.Offer.Adonis.ReadingFileFailedTitle = "Failure reading file";
Signavio.I18N.Repository.Offer.Adonis.ReadingFileFailedDesc = "The uploaded file could not be parsed as ADONIS XML. Please double check the appriate file type and ADONIS 2.0 XML structure or contact our <a href='mailto:[supportMailAdresse]'>support</a> in order to solve the problem.";

// Adonis Warnings dialog
Signavio.I18N.Repository.Offer.Adonis.warnings = "The import process of the ADONIS XML file failed. Please check out the error and warning messages in advance or contact the <a href='mailto:[supportMailAdresse]'>Signavio support</a> in order to solve the problem. Please include the applicable file in the support request, if possible.";
Signavio.I18N.Repository.Offer.Adonis.TitleWarnings = "Warnings";
Signavio.I18N.Repository.Offer.Adonis.TitleErrors = "Errors";
Signavio.I18N.Repository.Offer.Adonis.TitleInfos = "Infos";
Signavio.I18N.Repository.Offer.Adonis.HeaderTypeWarnings = "Warning Type";
Signavio.I18N.Repository.Offer.Adonis.HeaderTypeErrors = "Error Type";
Signavio.I18N.Repository.Offer.Adonis.HeaderTypeInfos = "Info Type";
Signavio.I18N.Repository.Offer.Adonis.HeaderDetailsWarnings = "Warning Details";
Signavio.I18N.Repository.Offer.Adonis.HeaderDetailsErrors = "Error Details";
Signavio.I18N.Repository.Offer.Adonis.HeaderDetailsInfos = "Info Details";
Signavio.I18N.Repository.Offer.Adonis.WarningDesc = "The import process of the ADONIS XML file could not be completed successfully. Please check out the error and warning messages in advance or contact the <a href='mailto:[supportMailAdresse]'>Signavio support</a> in order to solve the problem. Please include the applicable file in the support request, if possible.";
Signavio.I18N.Repository.Offer.Adonis.ErrorDesc = "The import process of the ADONIS XML file failed. Please check out the error and warning messages in advance or contact the <a href='mailto:[supportMailAdresse]'>Signavio support</a> in order to solve the problem. Please include the applicable file in the support request, if possible.";
Signavio.I18N.Repository.Offer.Adonis.OK = "OK";


//SAPERIONDummyEntries
if(!Signavio.I18N.SAPERIONDummyEntries) Signavio.I18N.SAPERIONDummyEntries = {};
Signavio.I18N.SAPERIONDummyEntries.processMap = "Value Chain";
Signavio.I18N.SAPERIONDummyEntries.epc = "Event-driven process chain (EPC)";
Signavio.I18N.SAPERIONDummyEntries.organizationalChart = "Organization Chart";
Signavio.I18N.SAPERIONDummyEntries.premiumFeature = "This feature is only available in the Signavio Enterprise Edition.";

//TIMDummyEntries
if(!Signavio.I18N.TIMDummyEntries) Signavio.I18N.TIMDummyEntries = {};
Signavio.I18N.TIMDummyEntries.processMap = "Value Chain";
Signavio.I18N.TIMDummyEntries.conversation = "Conversation Diagram (BPMN 2.0)";
Signavio.I18N.TIMDummyEntries.choreography = "Choreography Diagram (BPMN 2.0)";
Signavio.I18N.TIMDummyEntries.epc = "Event-driven process chain (EPC)";
Signavio.I18N.TIMDummyEntries.organizationalChart = "Organization Chart";
Signavio.I18N.TIMDummyEntries.premiumFeature = "This feature is only available in the Signavio Enterprise Edition.";

if(!Signavio.I18N.Repository.Configuration) Signavio.I18N.Repository.Configuration = {};
Signavio.I18N.Repository.Configuration.offerTitle = "Edit portal/explorer configuration ";
Signavio.I18N.Repository.Configuration.offerDescription = "Edit the configuration of the process portal and the explorer.";
Signavio.I18N.Repository.Configuration.offerLanguages = "Define languages";
Signavio.I18N.Repository.Configuration.offerSecurity = "Edit security configuration";

if(!Signavio.I18N.Repository.Configuration) {Signavio.I18N.Repository.Configuration = {};}
if(!Signavio.I18N.Repository.Configuration.Templates) {Signavio.I18N.Repository.Configuration.Templates = {};}
Signavio.I18N.Repository.Configuration.defaultdiagramTitle = "Entry point";
Signavio.I18N.Repository.Configuration.defaultdiagramDescription = "Decide, which diagram should be the entry point of the portal.";
Signavio.I18N.Repository.Configuration.autocompleteTitle = "Dictionary auto-completion in search field";
Signavio.I18N.Repository.Configuration.autocompleteDescription = "Enable or disable the auto-completion functionality in the search field of the navigation bar.";
Signavio.I18N.Repository.Configuration.enablebreadcrumbs = "Show Model path navigation";
Signavio.I18N.Repository.Configuration.enablebreadcrumbsDescription = "Enable or disable the model path navigation in the portal.";
Signavio.I18N.Repository.Configuration.enablecommentsTitle = "Comments";
Signavio.I18N.Repository.Configuration.enablecommentsDescription = "Should comments be shown or not? Decide the access, which collaborators have to comments.";
Signavio.I18N.Repository.Configuration.enablecommentsshow = "Create and show";
Signavio.I18N.Repository.Configuration.enablecommentsadd = "Create, but don't show";
Signavio.I18N.Repository.Configuration.enablecommentsnone = "Don't create and don't show";
Signavio.I18N.Repository.Configuration.portalleftfolderconfigTitle = "Folder overview";
Signavio.I18N.Repository.Configuration.portalleftfolderconfigDescription = "Define the default view setting for the folder overview on the left side of the process portal. It can be expanded, collapsed or disabled.";
Signavio.I18N.Repository.Configuration.portalleftfolderconfigOpened = "Expanded";
Signavio.I18N.Repository.Configuration.portalleftfolderconfigClosed = "Collapsed";
Signavio.I18N.Repository.Configuration.portalleftfolderconfigHidden= "Disabled";
Signavio.I18N.Repository.Configuration.infotemplateTitle = "Title template";
Signavio.I18N.Repository.Configuration.infotemplateDescription = "Decide, which additional information apart from the title should be shown in the header of the process portal.";
Signavio.I18N.Repository.Configuration.infotemplatelastEdit = "'last changed...'";
Signavio.I18N.Repository.Configuration.infotemplatelastPublish = "'published on...'";
Signavio.I18N.Repository.Configuration.infotemplatelastPublishBefore = "'published since...'";
Signavio.I18N.Repository.Configuration.infotemplatenone = "- None -";
Signavio.I18N.Repository.Configuration.printTitle = "Show print button";
Signavio.I18N.Repository.Configuration.printmDescription = "Show or hide the print button to allow or forbid collaborators to print diagrams from the process portal.";
Signavio.I18N.Repository.Configuration.logolinkTitle = "Link behind the logo";
Signavio.I18N.Repository.Configuration.logolinkDescription = "Please specify the URL which is used behind the logo.";
Signavio.I18N.Repository.Configuration.completelinkTitle = "Show process link on the whole element";
Signavio.I18N.Repository.Configuration.completelinkDescription = "Decide whether to show the link only over the area of the link symbol or over the whole element.";
Signavio.I18N.Repository.Configuration.templateTitle = "Template";
Signavio.I18N.Repository.Configuration.templateDescription = "Please specify the used template in the Portal.";
Signavio.I18N.Repository.Configuration.expandedrightpanelTitle = "Selected Panel";
Signavio.I18N.Repository.Configuration.expandedrightpanelDescription = "Define, if or which right hand panel should be shown.";
Signavio.I18N.Repository.Configuration.expandedrightpanelInfo = "Info";
Signavio.I18N.Repository.Configuration.expandedrightpanelLegend = "Legend";
Signavio.I18N.Repository.Configuration.expandedrightpanelComment = "Comment";
Signavio.I18N.Repository.Configuration.expandedrightpanelNone = "- None -";
Signavio.I18N.Repository.Configuration.urlhelplistTitle = "Links of Help";
Signavio.I18N.Repository.Configuration.urlhelplistDescription = "Here you can define a list of URLs which are shown under the toolbar menu 'Help'. <i>After changing this value you have to reload the application.</i>";
Signavio.I18N.Repository.Configuration.sharepointUrlTitle = "SharePoint Connector";
Signavio.I18N.Repository.Configuration.sharepointConnectorFailed = "SharePoint Connector is not available.";
Signavio.I18N.Repository.Configuration.sharepointUrlDescription = "The LDAP support of the Signavio Microsoft SharePoint component requires you to define the URL to the Microsoft SharePoint connector service. This will be used to request available groups and users from the internal Active Directory for single sign on to the Microsoft SharePoint component. If the URL changes, it has to be reconfigured here.";
Signavio.I18N.Repository.Configuration.sharepointUrlServiceAvailable = "The URL was successfully saved and the service is available.";
Signavio.I18N.Repository.Configuration.sharepointUrlServiceNotAvailable = "The URL was successfully saved, but the service is not available.";
Signavio.I18N.Repository.Configuration.sharepointUrlFailed = "The URL could not been saved. Please make sure that the URL and the security token are valid.";
Signavio.I18N.Repository.Configuration.sharepointUrlWaiting = "The URL was successfully saved. The service will now be tested for its availability.";
Signavio.I18N.Repository.Configuration.sharepointUrl = "Connector URL";
Signavio.I18N.Repository.Configuration.sharepointUrlToken = "Security token";
Signavio.I18N.Repository.Configuration.sharepointAzureTenantid = "Windows Azure Tenant Id";
Signavio.I18N.Repository.Configuration.sharepointAzureDomain = "Windows Azure AD Domain";
Signavio.I18N.Repository.Configuration.sharepointPrincipalId = "Principal ID";
Signavio.I18N.Repository.Configuration.sharepointAzureSymKey = "Symmetric Key";
Signavio.I18N.Repository.Configuration.sharepointUrlBtnTitle = "Update";
Signavio.I18N.Repository.Configuration.sharepointDownloadDesc = "Before you can download the Signavio Microsoft SharePoint component, you have to configure the authentication mode as well as the Microsoft SharePoint version being used.";
Signavio.I18N.Repository.Configuration.fittomodels = "Fit to diagram";
Signavio.I18N.Repository.Configuration.fittomodelsDescription = "Set the intial zoom level to the level where the whole diagram is visible.";
Signavio.I18N.Repository.Configuration.loading = "Loading the configuration...";
Signavio.I18N.Repository.Configuration.uilanguageTitle = "Application language";
Signavio.I18N.Repository.Configuration.uilanguageDescription = "Please define the language which should be used in the Portal by default.";
Signavio.I18N.Repository.Configuration.uilanguageAutomatic = "Automatic";
Signavio.I18N.Repository.Configuration.currencyTitle = "Currency";
Signavio.I18N.Repository.Configuration.currencyDescription = "Please define the currency which should be used by default.";
Signavio.I18N.Repository.Configuration.showRisksTitle = "Show risk and controls";
Signavio.I18N.Repository.Configuration.showRisksDescription = "Decide whether risks and controls shall be shown in the speech bubble as long as they are defined.";
Signavio.I18N.Repository.Configuration.showCustomLayersTitle = "Show attribute visualization";
Signavio.I18N.Repository.Configuration.showCustomLayersDescription = "Decide whether the attribute visualization shall be shown in the portal.";

if(!Signavio.I18N.Repository.ControlRights) Signavio.I18N.Repository.ControlRights = {};
Signavio.I18N.Repository.ControlRights.warning = "Warning";
Signavio.I18N.Repository.ControlRights.serverIpInvalid = "Error while saving your settings. At least one IP address was invalid.";

Signavio.I18N.Repository.ControlRights.serverError = "Error while saving your settings. Please contact the support.";
Signavio.I18N.Repository.ControlRights.ipCannotBeRemoved = "The selected IP address cannot be deleted.";
Signavio.I18N.Repository.ControlRights.ipIsInvalid = "An invalid IP address has been entered.";
Signavio.I18N.Repository.ControlRights.ipAlready = "The entered IP address already exists.";
Signavio.I18N.Repository.ControlRights.ipRange = "IP address filter";

Signavio.I18N.Repository.ControlRights.ipCheckTitle = "Activate address filter";
Signavio.I18N.Repository.ControlRights.ipCheckInfo = "You are about to activate the IPv4 addressefilter. Onced saved only networks with a listed IPv4 address will be able to contact this tenant.";
Signavio.I18N.Repository.ControlRights.ipListTitle = "Trusted addresses";
Signavio.I18N.Repository.ControlRights.generalDialogDescription = "Define control directives";

Signavio.I18N.Repository.ControlRights.offerTitle = "Control directives";
Signavio.I18N.Repository.ControlRights.offerDescription = "Configure control directives for the Process Portal and the Signavio Process Explorer";
Signavio.I18N.Repository.ControlRights.ipDialogDescription = "Limit the access to your workspace to certain IPv4 addresses. The IPv4 address is checked for modeling users, as well as read-only access and invitations to comment. The IPv4 address that corresponds to your current access point is added automatically and therefore it cannot be removed.";

Signavio.I18N.Repository.ControlRights.ppError = "The password you have chosen does not comply with the security requirements";
Signavio.I18N.Repository.ControlRights.passwordPolicies = "Password policies";
Signavio.I18N.Repository.ControlRights.passwordDialogDescription = "The password policies define security requirements that apply to all users when choosing a password.";
Signavio.I18N.Repository.ControlRights.ppComplexTitle = "Complexity requirements";
Signavio.I18N.Repository.ControlRights.ppComplexDescription = "To meet the complexity requirements, a password must fulfill three of the four following requirements: It must contain at least one capital letter (A to Z), one lower case letter (a to z), one number (0 to 9) and one special character (!,§,$,%,&,?,#).";
Signavio.I18N.Repository.ControlRights.ppUsernameCriteriaTitle = "Consider user name";
Signavio.I18N.Repository.ControlRights.ppUsernameCriteriaDescription = "The password must not contain the user's first or last name.";
Signavio.I18N.Repository.ControlRights.ppCharacterCriteriaTitle = "Consider user name (strict)";
Signavio.I18N.Repository.ControlRights.ppCharacterCriteriaDescription = "The password must not contain more than two letters that the user's first or last name contains in the same order.";
Signavio.I18N.Repository.ControlRights.ppMaxAgeTitle = "Maximum password age";
Signavio.I18N.Repository.ControlRights.ppMaxAgeDescription = "After the entered number of days have passed, the user must choose a new password.";
Signavio.I18N.Repository.ControlRights.ppMinAgeTitle = "Minimum password age";
Signavio.I18N.Repository.ControlRights.ppMinAgeDescription = "Forbids choosing a new password before the entered number of days have passed.";
Signavio.I18N.Repository.ControlRights.ppMaxLengthTitle = "Maximum password length";
Signavio.I18N.Repository.ControlRights.ppMaxLengthDescription = "The maximum number of characters a password can consist of.";
Signavio.I18N.Repository.ControlRights.ppMinLengthTitle = "Minimum password length";
Signavio.I18N.Repository.ControlRights.ppMinLengthDescription = "Minimum number of characters a password must consist of.";
Signavio.I18N.Repository.ControlRights.ppChronicTitle = "Password history";
Signavio.I18N.Repository.ControlRights.ppChronicDescription = "Forbids choosing one of a user's last passwords. The entered value indicates how many passwords to remember and to prevent the re-election.";

Signavio.I18N.Repository.ControlRights.invalid = "Your input is invalid.";
Signavio.I18N.Repository.ControlRights.days = "days";
Signavio.I18N.Repository.ControlRights.characters = "characters";
Signavio.I18N.Repository.ControlRights.passwords = "passwords";
Signavio.I18N.Repository.ControlRights.chancel = "Cancel";
Signavio.I18N.Repository.ControlRights.add = "Add";
Signavio.I18N.Repository.ControlRights.remove = "Remove";
Signavio.I18N.Repository.ControlRights.ipv4 = "Please enter an IPv4 addresse in the format 'XXX.XXX.XXX.XXX'. ";
Signavio.I18N.Repository.ControlRights.addAddressTitle = "Add IPv4 addresse";
Signavio.I18N.Repository.ControlRights.save = "Save";
Signavio.I18N.Repository.ControlRights.ownIp = " (own)";
Signavio.I18N.Repository.ControlRights.fieldEmpty = "Can not be blank.";
Signavio.I18N.Repository.ControlRights.ipInvalid = "The entered IP address is invalid. Please be aware of the IPv4 format and that some IPv4 addresses are reserved for a certain purpose (e.g. 127.0.0.0) and can not be selected.";
Signavio.I18N.Repository.ControlRights.lessThanMin = "The minimum value for this field is {0}.";
Signavio.I18N.Repository.ControlRights.moreThanMax = "The maximum value for this field is {0}.";
Signavio.I18N.Repository.ControlRights.saveFailed =  "Failed to save the configuration. Please try again or contact the <a href='mailto:[supportMailAdresse]'>support</a>.";

Signavio.I18N.Repository.Configuration.general = "General";
Signavio.I18N.Repository.Configuration.Templates.signavio = "Signavio";
Signavio.I18N.Repository.Configuration.Templates.aok = "AOK";
Signavio.I18N.Repository.Configuration.Templates.green = "Green";
Signavio.I18N.Repository.Configuration.Templates.blue = "Blue";
Signavio.I18N.Repository.Configuration.Templates.aquamarin = "Aquamarin";
Signavio.I18N.Repository.Configuration.Templates.gray = "Gray";
Signavio.I18N.Repository.Configuration.Templates.brCompany = "BR";
Signavio.I18N.Repository.Configuration.Templates.bigPointCompany = "BIGPOINT";
Signavio.I18N.Repository.Configuration.Templates.hugoBossCompany = "HUGO BOSS";
Signavio.I18N.Repository.Configuration.Templates.pumaCompany = "PUMA";
Signavio.I18N.Repository.Configuration.Templates.grunenthalCompany = "Grünenthal";
Signavio.I18N.Repository.Configuration.Templates.zhawCompany = "ZHAW";
Signavio.I18N.Repository.Configuration.Templates.spardabankCompany = "Sparda-Bank";
Signavio.I18N.Repository.Configuration.Templates.windmoellerCompany = "Windmöller & Hölscher";
Signavio.I18N.Repository.Configuration.Templates.cittiCompany = "Citti";
Signavio.I18N.Repository.Configuration.Templates.stadtwerkeDessauCompany = "Stadtwerke Dessau";
Signavio.I18N.Repository.Configuration.Templates.headstrongCompany = "Headstrong";
Signavio.I18N.Repository.Configuration.Templates.echCompany = "eCH";
Signavio.I18N.Repository.Configuration.Templates.lwbCompany = "LWB";
Signavio.I18N.Repository.Configuration.Templates.kpmgCompany = "KPMG";
Signavio.I18N.Repository.Configuration.Templates.comcastCompany = "Comcast";
Signavio.I18N.Repository.Configuration.Templates.deutscheBKKCompany = "Deutsche BKK";

if (!Signavio.I18N.FilterSet) { Signavio.I18N.FilterSet = {}; }
Signavio.I18N.FilterSet.description = "Define rules which will be used to select models for the export. The first dropdown dialog can further be used to select the folder, you want to search in.";
Signavio.I18N.FilterSet.change = "Change";
Signavio.I18N.FilterSet.emptyText = "There are currenlty no filters.";
Signavio.I18N.FilterSet.chooseFolder = "Choose a different folder...";
Signavio.I18N.FilterSet.chooseFolderEmptyText = "Please choose a folder.";
Signavio.I18N.FilterSet.errorStart = "The filter on \"";
Signavio.I18N.FilterSet.errorEnd = "\" is invalid.";
Signavio.I18N.FilterSet.waitingMessage = "Filtering diagrams. Please wait...";
Signavio.I18N.FilterSet.emptyResultHeading = "Attention";
Signavio.I18N.FilterSet.emptyResultWarning = "Your search produced an empty result. No diagrams were selected.";
Signavio.I18N.FilterSet.modelName = "Name";
Signavio.I18N.FilterSet.modelDescription = "Description";
Signavio.I18N.FilterSet.modelLastModified = "Last modified";
Signavio.I18N.FilterSet.modelIsPublished = "Is published";
Signavio.I18N.FilterSet.modelEditor = "Edited by";
Signavio.I18N.FilterSet.modelStencilset = "Diagram type";
Signavio.I18N.FilterSet.timeFormat = "m.j.Y";
Signavio.I18N.FilterSet.timeFrom = "From";
Signavio.I18N.FilterSet.timeTill = "Till";
Signavio.I18N.FilterSet.requestError = "An error occurred while searching for diagrams. Please try again later.";
Signavio.I18N.FilterSet.folderPickerHead = "Choose a folder";
Signavio.I18N.FilterSet.folderPickerDescription = "Please select a folder.";
Signavio.I18N.FilterSet.buttonAdd = "Add filter";
Signavio.I18N.FilterSet.windowTitle = "Edit filters";
Signavio.I18N.FilterSet.filtersActive = "Filter(s) active";
Signavio.I18N.FilterSet.invalidFilterWarning = "Invalid filters will be ignored. Do you want to continue?";
Signavio.I18N.FilterSet.decimalSeparator = ".";
Signavio.I18N.FilterSet.errorNaN = "This value is not a number!";
Signavio.I18N.FilterSet.diagramsFoundStart = "";
Signavio.I18N.FilterSet.diagramsFoundEnd = " found, that match your query.";
Signavio.I18N.FilterSet.header = {
		field: "Attribute",
		operation: "Operation",
		value: "Value"
};
Signavio.I18N.FilterSet.bool = {
		typeTrue: "true",
		typeFalse: "false"
};
Signavio.I18N.FilterSet.relation = {
		typeEquals: "equals",
		typeNotEqual: "not equal",
		typeContains: "contains",    // TODO: ordentlich vom deutschen übersetzen
		typeNotContains: "does not contain", // TODO: ordentlich vom deutschen übersetzen
		typeBeginsWith: "begins with",
		typeEndsWith: "ends with",
		typeIsEmpty: "is empty"
};
Signavio.I18N.FilterSet.date = {
		typeBefore: "before",
		typeOn: "on",
		typeAfter: "after",
		typeBetween: "zwischen"
};
Signavio.I18N.FilterSet.number = {
		typeLessThan: "is less",
		typeLessThanEqual: "is less or equal",
		typeEqual: "is equal",
		typeNotEqual: "is not equal",
		typeGreaterThanEqual: "is greater or equal",
		typeGreaterThan: "is greater"
};
Signavio.I18N.FilterSet.glossary = {
		typeEqual: "linked with"
};
Signavio.I18N.FilterSet.groups = ["Diagram information",
                                  "Custom diagram attributes"];


Signavio.I18N.Repository.Offer.manageInternalFolderTitle = "Show process documentation templates";
Signavio.I18N.Repository.Offer.manageInternalFolderDesc = "";

/* Multilanguage */
if(!Signavio.I18N.Languages) { Signavio.I18N.Languages = {}; }
Signavio.I18N.Languages = {
		bg: "Bulgarian",
		cs: "Czech",
		da: "Danish",
		nl: "Dutch",
		et: "Estonian",
		fi: "Finnish",
		fr: "French",
		de: "German",
		el: "Greek",
		hu: "Hungarian",
		ie: "Irish",
		it: "Italian",
		lv: "Latvian",
		lt: "Lithunian",
		mt: "Maltese",
		pl: "Polish",
		pt: "Portuguese",
		ro: "Romanian",
		sk: "Slovak",
		sl: "Slovene",
		es: "Spanish",
		sv: "Swedish",
		mx: "Spanish",
		en: "English",
		lb: "Luxembourgish",
		af: "Afrikaans"
};

Signavio.I18N.Countries = {
		at: "Austria",
		be: "Belgium",
		bg: "Bulgaria",
		ch: "Switzerland",
		cz: "Czech Republic",
		dk: "Denmark",
		gb: "Great Britain",
		nl: "Netherlands",
		ee: "Estonia",
		fi: "Finland",
		fr: "France",
		de: "Germany",
		gr: "Greece",
		hu: "Hungary",
		ie: "Ireland",
		it: "Italy",
		lv: "Latvia",
		lt: "Lithuania",
		mt: "Malta",
		pl: "Poland",
		pt: "Portugal",
		ro: "Romänien",
		sk: "Slovakia",
		si: "Slovenia",
		es: "Spain",
		se: "Sweden",
		en: "England",
		mx: "Mexico",
		us: "USA",
		lu: "Luxembourg",
		za: "South Africa",
		br: "Brazil",
		ca: "Canada",
		cl: "Chile",
		li: "Liechtenstein",
		nz: "New Zealand",
		au: "Australia"
};

Signavio.I18N.DateFormat = {
		title: "Date Format:",
		description: "Configure different date formats for each language.",
		Format: {
			day: "Day",
			month: "Month",
			year: "Year"
		}
	};

if(!Signavio.I18N.LanguageConfiguration) { Signavio.I18N.LanguageConfiguration = {}; }
Signavio.I18N.LanguageConfiguration.tabTitle = "Language settings";
Signavio.I18N.LanguageConfiguration.remove = "Remove";
Signavio.I18N.LanguageConfiguration.activeLanguages = "Active languages:";
Signavio.I18N.LanguageConfiguration.activeLangaugesDescription = "The list of languages the diagram's textual content can be defined in your workspace. If you do not longer need a langauge, click \"Remove\" to delete it. Please be aware, the first language in the list defines the default language.";
Signavio.I18N.LanguageConfiguration.availableLanguages = "Available languages";
Signavio.I18N.LanguageConfiguration.addLanguage = "Add language";
Signavio.I18N.LanguageConfiguration.addLanguageDescription = "Please choose all languages in which your contents should be available.";
Signavio.I18N.LanguageConfiguration.up = "move up";
Signavio.I18N.LanguageConfiguration.moveConfirmTitle = "Default language";
Signavio.I18N.LanguageConfiguration.moveConfirmDescription = "You are about to change the default language to #{lang}. This has a major impact on your workspace, e.g. you have to open and save <b>all</b> diagrams again in order to see a correct diagram preview.<br/>Please contact the <a href='mailto:#{supportMailAdresse}'>support</a> to get an execution code.<br/><br/><label for='x-language-change-default'>Execution code: </label><input id='x-language-change-default' type='text' style='width:50px;margin-left:10px;padding:1px 2px;' maxlength='4'><br/><br/>Do you want to continue?";
Signavio.I18N.LanguageConfiguration.addNewTitle = "New language";
Signavio.I18N.LanguageConfiguration.addNewDescription = "You are about to activate the multi language definition for your workspace. This action cannot be reverted. Your first selected language will become the default language for the workspace and cannot be changed later.<br/>Do you want to continue?";
Signavio.I18N.LanguageConfiguration.emptyText = "Please add a new language.";
Signavio.I18N.LanguageConfiguration.tooManyLangsTitle = "Maximum number of languages reached";
Signavio.I18N.LanguageConfiguration.tooManyLangsDescription = "You have reached the maximum number of available languages for the licence you purchased.";

if (!Signavio.I18N.Repository.Layers) { Signavio.I18N.Repository.Layers = {}; }
Signavio.I18N.Repository.Layers.title = "Attribute visualization";
Signavio.I18N.Repository.Layers.offerTitle = "Manage attribute visualization";
Signavio.I18N.Repository.Layers.description = "You can define different layers that help you to visualize the values of your custom attributes while working with the graphical process editor. For every layer you can choose a name and an icon, and define several rules. The visualization can be triggered within the process editor with a separate button for every layer.";
Signavio.I18N.Repository.Layers.addNewLayer = "Add new layer";
Signavio.I18N.Repository.Layers.removeLayer = "Remove layer";
Signavio.I18N.Repository.Layers.addNewRuleSet = "Add new rules";
Signavio.I18N.Repository.Layers.removeRuleSet = "Remove rules";
Signavio.I18N.Repository.Layers.addNewRule = "Add new rule";
Signavio.I18N.Repository.Layers.removeRule = "Remove rule";
Signavio.I18N.Repository.Layers.ruleSetEmptyText = "Define rules";
Signavio.I18N.Repository.Layers.ruleEmptyText = "Define rules";
Signavio.I18N.Repository.Layers.newLayer = "New Layer";
Signavio.I18N.Repository.Layers.ruleSet = "rule";
Signavio.I18N.Repository.Layers.ruleSets = "rules";
Signavio.I18N.Repository.Layers.andMore = "and {number} more";
Signavio.I18N.Repository.Layers.errorTitle = "Attribute Visualization";
Signavio.I18N.Repository.Layers.errorMsg = "Failed to save the configuration. Please contact the support.";
if (!Signavio.I18N.Repository.Layers.Header) { Signavio.I18N.Repository.Layers.Header = {}; }
Signavio.I18N.Repository.Layers.Header.name = "Name";
Signavio.I18N.Repository.Layers.Header.visualization = "Visualization";
Signavio.I18N.Repository.Layers.Header.ruleSets = "Rules";
Signavio.I18N.Repository.Layers.Header.color = "Color";
Signavio.I18N.Repository.Layers.Header.property = "Property";
Signavio.I18N.Repository.Layers.Header.relation = "Relation";
Signavio.I18N.Repository.Layers.Header.value = "Value";
if (!Signavio.I18N.Repository.Layers.Relations) { 
	Signavio.I18N.Repository.Layers.Relations = {};
	Signavio.I18N.Repository.Layers.Relations.string = {};
	Signavio.I18N.Repository.Layers.Relations.number = {};
	Signavio.I18N.Repository.Layers.Relations.boolean = {};
	Signavio.I18N.Repository.Layers.Relations.choice = {};
	Signavio.I18N.Repository.Layers.Relations.date = {};
	Signavio.I18N.Repository.Layers.Relations.modellink = {};
	Signavio.I18N.Repository.Layers.Relations.glossary = {};
	Signavio.I18N.Repository.Layers.Relations.url = {};
}
Signavio.I18N.Repository.Layers.Relations.string.equals = "is";
Signavio.I18N.Repository.Layers.Relations.string.equalsNot = "is not";
Signavio.I18N.Repository.Layers.Relations.string.contains = "contains";
Signavio.I18N.Repository.Layers.Relations.string.startsWith = "starts with";
Signavio.I18N.Repository.Layers.Relations.string.endsWith = "ends with";
Signavio.I18N.Repository.Layers.Relations.string.isEmpty = "is empty";
Signavio.I18N.Repository.Layers.Relations.string.isNotEmpty = "is not empty";
Signavio.I18N.Repository.Layers.Relations.string.containsEntry = "contains entry";
Signavio.I18N.Repository.Layers.Relations.number.equals = "=";
Signavio.I18N.Repository.Layers.Relations.number.lt = "<";
Signavio.I18N.Repository.Layers.Relations.number.lte = "<=";
Signavio.I18N.Repository.Layers.Relations.number.gte = ">=";
Signavio.I18N.Repository.Layers.Relations.number.gt = ">";
Signavio.I18N.Repository.Layers.Relations.boolean.isTrue = "is true";
Signavio.I18N.Repository.Layers.Relations.boolean.isFalse = "is false";
Signavio.I18N.Repository.Layers.Relations.choice.equals = "is";
Signavio.I18N.Repository.Layers.Relations.choice.equalsNot = "is not";
Signavio.I18N.Repository.Layers.Relations.choice.isEmpty = "is empty";
Signavio.I18N.Repository.Layers.Relations.choice.isNotEmpty = "is not empty";
Signavio.I18N.Repository.Layers.Relations.date.isEmpty = "is empty";
Signavio.I18N.Repository.Layers.Relations.date.isNotEmpty = "is not empty";
Signavio.I18N.Repository.Layers.Relations.modellink.isEmpty = "is empty";
Signavio.I18N.Repository.Layers.Relations.modellink.isNotEmpty = "is not empty";
Signavio.I18N.Repository.Layers.Relations.glossary.isEmpty = "is empty";
Signavio.I18N.Repository.Layers.Relations.glossary.isNotEmpty = "is not empty";
Signavio.I18N.Repository.Layers.Relations.url.isEmpty = "is empty";
Signavio.I18N.Repository.Layers.Relations.url.isNotEmpty = "is not empty";
if (!Signavio.I18N.Repository.Layers.Custom) { Signavio.I18N.Repository.Layers.Custom = {}; }
Signavio.I18N.Repository.Layers.Custom.name = "Name";
Signavio.I18N.Repository.Layers.Custom.documentation = "Documentation";
Signavio.I18N.Repository.Layers.Custom.costs = "Execution costs";
Signavio.I18N.Repository.Layers.Custom.time = "Execution time (min)";
Signavio.I18N.Repository.Layers.Custom.costcenter = "Cost Center";
Signavio.I18N.Repository.Layers.Custom.applyincalc = "Apply in calculation";
Signavio.I18N.Repository.Layers.Custom.frequency = "Frequency (per Year)";
if (!Signavio.I18N.Repository.Layers.Visualization) { Signavio.I18N.Repository.Layers.Visualization = {}; }
Signavio.I18N.Repository.Layers.Visualization.PROPERTY = "Property value";
Signavio.I18N.Repository.Layers.Visualization.ICON1 = "Finance Icon";
Signavio.I18N.Repository.Layers.Visualization.ICON2 = "Human Icon";
Signavio.I18N.Repository.Layers.Visualization.ICON3 = "Technology Icon";
Signavio.I18N.Repository.Layers.Visualization.ICON4 = "Event Icon";

if (!Signavio.I18N.Filestore) {Signavio.I18N.Filestore = {}; }
Signavio.I18N.Filestore.warningTTip = "The file contingent for your workspace is almost exhausted. Please delete unused files or contact the Signavio Support to order additional storage.";
Signavio.I18N.Filestore.warningText = " of file contingent in use";
Signavio.I18N.Filestore.uploadTitle = "Upload document/picture";
Signavio.I18N.Filestore.updateFileDesc = "Please select a document or picture which should replace the currently selected document.";
Signavio.I18N.Filestore.updatePictureDesc = "Please select a document or picture which should replace the currently selected picture.";
Signavio.I18N.Filestore.uploadDesc = "Please specify a document or picture that will be uploaded to your own Siganvio file storage.";
Signavio.I18N.Filestore.uploadFile = "File";
Signavio.I18N.Filestore.uploadFileName = "Name";
Signavio.I18N.Filestore.uploadRevisionComment = "Revision comment";
Signavio.I18N.Filestore.configTitle = "Enable uploading of documents/pictures";
Signavio.I18N.Filestore.configDesc = "Enable the upload of documents/pictures into your Signavio file storage.<br/>Your file storage can contain up to ";
Signavio.I18N.Filestore.uploadButton = "Upload";
Signavio.I18N.Filestore.ago = "{0} ago";
Signavio.I18N.Filestore.author = "Author:";
Signavio.I18N.Filestore.changed = "Changed:";
Signavio.I18N.Filestore.fileType = "File type:";
Signavio.I18N.Filestore.fileSize = "File size:";
Signavio.I18N.Filestore.unknownFileFormat = "Unknown file format";
Signavio.I18N.Filestore.downloadFile = "Download file";
Signavio.I18N.Filestore.document = "document";
Signavio.I18N.Filestore.picture = "picture";
Signavio.I18N.Filestore.archive = "Archive";
Signavio.I18N.Filestore.update = "Update {0}";
Signavio.I18N.Filestore.maximalFileSize = "The maximum file size is ";
Signavio.I18N.Filestore.youCurrentlyUse = "You currently use ";
Signavio.I18N.Filestore.selectDocumentPicture = "Select document/picture";
Signavio.I18N.Filestore.documentPictureTooBig = "Document/picture too big";
Signavio.I18N.Filestore.documentPictureTooBigDesc = "The document/picture is too big or would exceed the size of your Signavio file storage. Please delete unused files or contact the Signavio Support to order additional storage.";
Signavio.I18N.Filestore.uploadInProgress = "Upload in progress...";
Signavio.I18N.Filestore.manageFilesInTreeTitle = "Show relevant documents/pictures in the document tree";
Signavio.I18N.Filestore.manageFilesInTreeDesc = "Documents/pictures are shown below the linking model inside of the document tree.";
Signavio.I18N.Filestore.downloadTitle = "Download document/picture";
Signavio.I18N.Filestore.download = "Download {0}";

if(!Signavio.I18N.TSystemsTemplate) { Signavio.I18N.TSystemsTemplate = {}; }
Signavio.I18N.TSystemsTemplate.documentsUrl = "URL of the iframe inside the documents tab";
Signavio.I18N.TSystemsTemplate.documentsUrlDescription = "Here you can define the url, that should be used for the iframe, that is created inside the documents tab in the portal.";

/** MetaModellingGuidelines - Plugin START **/
if(!Signavio.I18N.MetaModellingGuidelines) { Signavio.I18N.MetaModellingGuidelines = {}; }
if(!Signavio.I18N.MetaModellingGuidelines.Rules) { Signavio.I18N.MetaModellingGuidelines.Rules = {}; }

Signavio.I18N.MetaModellingGuidelines.modellingGuideLines = "Modeling conventions";
Signavio.I18N.MetaModellingGuidelines.modellingGuideLinesTabName = "Modeling rules";
Signavio.I18N.MetaModellingGuidelines.addConfig = "Add custom modeling convention";
Signavio.I18N.MetaModellingGuidelines.attributeChecker = "Attribute mappings";
Signavio.I18N.MetaModellingGuidelines.attributeCheckerDescription = "Please define a name and the set of attributes which should be considered in the convention.";
Signavio.I18N.MetaModellingGuidelines.newConfig = "New:";
Signavio.I18N.MetaModellingGuidelines.rulesHeader = "Rules";
Signavio.I18N.MetaModellingGuidelines.legendCould = "Information (hint)";
Signavio.I18N.MetaModellingGuidelines.legendShould = "Recommendation (warning)";
Signavio.I18N.MetaModellingGuidelines.legendMust = "Mandatory (error)";
Signavio.I18N.MetaModellingGuidelines.removeGuideline = "Remove convention";
Signavio.I18N.MetaModellingGuidelines.configName = "Convention";
Signavio.I18N.MetaModellingGuidelines.emptyConfigName = "Convention";
Signavio.I18N.MetaModellingGuidelines.copyFrom = "Copy from:";
Signavio.I18N.MetaModellingGuidelines.deleteConfig = "Do you really like to remove the modelling convention?";
Signavio.I18N.MetaModellingGuidelines.selectConfig = "Choose convention";
Signavio.I18N.MetaModellingGuidelines.addDescriptionText = "Here you can add custom modeling conventions to your workspace. To do so, you can either create a new empty convention or copy an existing convention.";
Signavio.I18N.MetaModellingGuidelines.withNameConfig = "with name:";
Signavio.I18N.MetaModellingGuidelines.importFrom = "Import from:";
Signavio.I18N.MetaModellingGuidelines.addConfigTitle = "Create new convention";
Signavio.I18N.MetaModellingGuidelines.addCutomRuleTitle = "Add new custom rule";
Signavio.I18N.MetaModellingGuidelines.cancelConfig = "Cancel";
Signavio.I18N.MetaModellingGuidelines.cancelCustomRule = "Cancel";
Signavio.I18N.MetaModellingGuidelines.addConfigButton = "Create";
Signavio.I18N.MetaModellingGuidelines.editCustomRuleButton = "Apply changes";
Signavio.I18N.MetaModellingGuidelines.removeCustomRuleButton = "Remove";
Signavio.I18N.MetaModellingGuidelines.removeCustomRuleConfirm = "Do you really like to remove the custom rule?";
Signavio.I18N.MetaModellingGuidelines.addCustomRuleButton = "Create";
Signavio.I18N.MetaModellingGuidelines.loadGrid = "Load modelling conventions...";
Signavio.I18N.MetaModellingGuidelines.atSave = "in save dialog";
Signavio.I18N.MetaModellingGuidelines.atButton = "per button";
Signavio.I18N.MetaModellingGuidelines.addConfigMissingName = "The new configuration should have a name.";
Signavio.I18N.MetaModellingGuidelines.addConfigMissingNameAndType = "The new configuration should have a name and there have to be a selected configuration, which is copying";
Signavio.I18N.MetaModellingGuidelines.errorRequest = "Unfortunately, an error occurred while the modeling conventions were edited. Please try again later or contact the Signavio support.";
Signavio.I18N.MetaModellingGuidelines.disableMG = "Deactivate convention";
Signavio.I18N.MetaModellingGuidelines.removeMG = "Remove convention";
Signavio.I18N.MetaModellingGuidelines.chooseOption = "Choose option";
Signavio.I18N.MetaModellingGuidelines.chooseOptions = "Choose options";
Signavio.I18N.MetaModellingGuidelines.addAttributeChecker = "Add";
Signavio.I18N.MetaModellingGuidelines.editAttributeChecker = "Edit";
Signavio.I18N.MetaModellingGuidelines.addNewCustomRule = "Add new custom rule";
Signavio.I18N.MetaModellingGuidelines.addNewRule = "Add new rule";
Signavio.I18N.MetaModellingGuidelines.editCustomRule = "Edit custom rule";
Signavio.I18N.MetaModellingGuidelines.emptyRuleName = "Rule name";
Signavio.I18N.MetaModellingGuidelines.ruleName = "Rule name";
Signavio.I18N.MetaModellingGuidelines.emptyRuleDescription = "Rule description";
Signavio.I18N.MetaModellingGuidelines.ruleDescription = "Rule description";
Signavio.I18N.MetaModellingGuidelines.addCustomRuleErrorTitle = "Add new custom rule";
Signavio.I18N.MetaModellingGuidelines.addCustomRuleErrorDescription  = "The new custom rule should have a name and a description.";
Signavio.I18N.MetaModellingGuidelines.headerInEditor  = "Check in editor:";
Signavio.I18N.MetaModellingGuidelines.moreThanFiveAtSaveConfigsTitle = "Modeling conventions";
Signavio.I18N.MetaModellingGuidelines.moreThanFiveAtSaveConfigsDesc = "You can only check up to 5 conventions automatically when a diagram is saved. Please deactivate another convention check first.";
Signavio.I18N.MetaModellingGuidelines.removeAttributeChecker = "Remove";
Signavio.I18N.MetaModellingGuidelines.closeAttributeChecker = "Close";
Signavio.I18N.MetaModellingGuidelines.removeAttributeCheckerTitle = "Remove";
Signavio.I18N.MetaModellingGuidelines.removeAttributeCheckerDesc = "Do you really like to remove this mandatory attribute set?";
Signavio.I18N.MetaModellingGuidelines.maxDescriptionLengthError = "The maximum length for this field is 1000";

/*   - - - - - - -  */
/*   General terms  */
/*   - - - - - - -  */
Signavio.I18N.MetaModellingGuidelines.Rules["FAILED"] = "Failed";
Signavio.I18N.MetaModellingGuidelines.Rules["PASSED"] = "Passed";
Signavio.I18N.MetaModellingGuidelines.descriptionTextUltimate = "In this step, you can activate different modeling conventions to be checked within your workspace. Apart from pre-configured conventions, you can define your custom conventions using a set of rules that can be checked automatically. The checks can be performed in an Excel-report as well as directly while modeling. Furthermore, you can configure conventions to be checked when a new diagram revision is saved.";
Signavio.I18N.MetaModellingGuidelines.descriptionTextCorporate = "Here you can activate different modeling conventions to be applied within your workspace. Checks can be done as Excel reports, directly while modeling or when saving a diagram.";
Signavio.I18N.MetaModellingGuidelines.Rules["zz_CustomRuleCheckerNaming"] = "Custom Rules";
Signavio.I18N.MetaModellingGuidelines.Rules["zz_CustomRuleCheckerNotation"] = "Custom Rules";
Signavio.I18N.MetaModellingGuidelines.Rules["zz_CustomRuleCheckerArchitecture"] = "Custom Rules";
Signavio.I18N.MetaModellingGuidelines.Rules["zz_CustomRuleCheckerLayout"] = "Custom Rules";
Signavio.I18N.MetaModellingGuidelines.Rules["zz_CustomRuleCheckerStructure"] = "Custom Rules";

Signavio.I18N.MetaModellingGuidelines.Rules["attr_documentation"] = "Activity Documentations";
Signavio.I18N.MetaModellingGuidelines.Rules["attr_must"] = "eCH Mandatory Attributes";
Signavio.I18N.MetaModellingGuidelines.Rules["attr_should"] = "eCH Recommendations";
Signavio.I18N.MetaModellingGuidelines.Rules["attr_subprocessref"] = "Mandatory Subprocess Link";

/*   - - - - - - -  */
/*   Combox Content */
/*   - - - - - - -  */
Signavio.I18N.MetaModellingGuidelines.Rules["prohibited"] = "Prohibited";
Signavio.I18N.MetaModellingGuidelines.Rules["required"] = "Required";

Signavio.I18N.MetaModellingGuidelines.Rules["Questions"] = "Questions";
Signavio.I18N.MetaModellingGuidelines.Rules["Conjunctions"] = "Conjunctions";

Signavio.I18N.MetaModellingGuidelines.Rules["consistence"] = "consistent usage";
Signavio.I18N.MetaModellingGuidelines.Rules["force"] = "mandatory usage";

//Questions
Signavio.I18N.MetaModellingGuidelines.Rules["a0"] = "DIN A0";
Signavio.I18N.MetaModellingGuidelines.Rules["a1"] = "DIN A1";
Signavio.I18N.MetaModellingGuidelines.Rules["a2"] = "DIN A2";
Signavio.I18N.MetaModellingGuidelines.Rules["a3"] = "DIN A3";
Signavio.I18N.MetaModellingGuidelines.Rules["a4"] = "DIN A4";

Signavio.I18N.MetaModellingGuidelines.Rules["letter"] = "Letter";
Signavio.I18N.MetaModellingGuidelines.Rules["ledger"] = "Ledger";
Signavio.I18N.MetaModellingGuidelines.Rules["broadsheet"] = "Broadsheet";
Signavio.I18N.MetaModellingGuidelines.Rules["ansid"] = "ANSI-D";
Signavio.I18N.MetaModellingGuidelines.Rules["ansie"] = "ANSI-E";

Signavio.I18N.MetaModellingGuidelines.Rules["horizontalOrVertically"] = "Horizontal/ Vertical";
Signavio.I18N.MetaModellingGuidelines.Rules["straight"] = "Straight";
Signavio.I18N.MetaModellingGuidelines.Rules["rightAngle"] = "Perpendicular";
Signavio.I18N.MetaModellingGuidelines.Rules["arbitrary"] = "Arbitrary";

Signavio.I18N.MetaModellingGuidelines.Rules["horizontal"] = "Horizontal";
Signavio.I18N.MetaModellingGuidelines.Rules["vertical"] = "Vertical";

Signavio.I18N.MetaModellingGuidelines.Rules["100"] = "100%";
Signavio.I18N.MetaModellingGuidelines.Rules["90"] = "90%";
Signavio.I18N.MetaModellingGuidelines.Rules["80"] = "80%";
Signavio.I18N.MetaModellingGuidelines.Rules["70"] = "70%";
Signavio.I18N.MetaModellingGuidelines.Rules["75"] = "75%";
Signavio.I18N.MetaModellingGuidelines.Rules["125"] = "125%";
Signavio.I18N.MetaModellingGuidelines.Rules["150"] = "150%";
Signavio.I18N.MetaModellingGuidelines.Rules["175"] = "175%";
Signavio.I18N.MetaModellingGuidelines.Rules["200"] = "200%";

/*   - - - - - -  */
/*   Categories   */
/*   - - - - - -  */
Signavio.I18N.MetaModellingGuidelines.Rules["ARCHITECTURE"] = "Architecture";
Signavio.I18N.MetaModellingGuidelines.Rules["NOTATION"] = "Notation";
Signavio.I18N.MetaModellingGuidelines.Rules["NAMING"] = "Naming";
Signavio.I18N.MetaModellingGuidelines.Rules["STRUCTURE"] = "Process structure";
Signavio.I18N.MetaModellingGuidelines.Rules["LAYOUT"] = "Layout";
Signavio.I18N.MetaModellingGuidelines.Rules["LEGEND"] = "Legend";		

/*   - - - - - - - - - -  */
/*   Architecture Checks  */
/*   - - - - - - - - - -  */
Signavio.I18N.MetaModellingGuidelines.Rules["DistinctDiagramNamesChecker"] = "Usage of unique diagram names";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_DistinctDiagramNamesChecker"] = "Checks if all diagram names are unique within the diagram set.";

Signavio.I18N.MetaModellingGuidelines.Rules["CommentChecker"] = "Incorporation of open comments";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_CommentChecker"] = "Checks if all comments have been incorporated into the diagram.";

Signavio.I18N.MetaModellingGuidelines.Rules["MaxNumActivitiesChecker"] = "Usage of a restricted number of activities";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_MaxNumActivitiesChecker"] = "Checks if diagrams are modeled using a maximum number of activities.";

Signavio.I18N.MetaModellingGuidelines.Rules["PrefixChecker"] = "Usage of a numbering schema in diagram names";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_PrefixChecker"] = "Checks if all diagram names contain a numbered prefix.";

/*   - - - - - - - -  */
/*   Notation Checks  */
/*   - - - - - - - -  */
Signavio.I18N.MetaModellingGuidelines.Rules["Aa_PaletteChecker"] = "Usage of a defined BPMN subset";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_Aa_PaletteChecker"] = "Checks if the defined BPMN sub set is used in the diagram.";

Signavio.I18N.MetaModellingGuidelines.Rules["AttributeChecker"] = "Definition of mandatory attributes";
Signavio.I18N.MetaModellingGuidelines.Rules["My_AttributeChecker"] = "My mandatory attribute set";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_AttributeChecker"] = "Checks if all mandatory attributes are set.";

Signavio.I18N.MetaModellingGuidelines.Rules["GlossaryChecker"] = "Definition of required dictionary links";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_GlossaryChecker"] = "Checks if elements are linked to dictionary entries.";

/*   - - - - - - - - -  */
/*   Liguistic Checks   */
/*   - - - - - - - - -  */
Signavio.I18N.MetaModellingGuidelines.Rules["MSEmptyEventLabelChecker"] = "\"Method and Style\"-conform labelings of events";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_MSEmptyEventLabelChecker"] = "Checks if all triggered start events, all intermediate events and all end events (in diagrams with several end states) are labeled.";

Signavio.I18N.MetaModellingGuidelines.Rules["EmptyLabelChecker"] = "Definition of required element names";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_EmptyLabelChecker"] = "Checks if elements are named.";

Signavio.I18N.MetaModellingGuidelines.Rules["DistinctChecker"] = "Usage of unique element names";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_DistinctChecker"] = "Checks if element names are unique within a diagram.";

Signavio.I18N.MetaModellingGuidelines.Rules["ActivityLabelChecker"] = "Usage of consistent activity naming style";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_ActivityLabelChecker"] = "Checks if activities are named using a consistent style.";

Signavio.I18N.MetaModellingGuidelines.Rules["XORLabelChecker"] = "Usage of consistent XOR gateway naming style";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_XORLabelChecker"] = "Checks if XOR gateways are named using a consistent style.";

Signavio.I18N.MetaModellingGuidelines.Rules["EventLabelChecker"] = "Usage of consistent event naming style";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_EventLabelChecker"] = "Checks if events are named using a consistent style.";

Signavio.I18N.MetaModellingGuidelines.Rules["SubprocessNamingChecker"] = "Consistent naming of subprocesses";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_SubprocessNamingChecker"] = "Checks if collapsed subprocesses have the same name as the embedded diagrams.";

Signavio.I18N.MetaModellingGuidelines.Rules["OI"] = "Object infinitive";
Signavio.I18N.MetaModellingGuidelines.Rules["VO"] = "Verb object";
Signavio.I18N.MetaModellingGuidelines.Rules["AN"] = "Action noun";
Signavio.I18N.MetaModellingGuidelines.Rules["MS"] = "Modal state description";
Signavio.I18N.MetaModellingGuidelines.Rules["ASS"] = "Adjective state description";
Signavio.I18N.MetaModellingGuidelines.Rules["CONJ"] = "Conjunction";
Signavio.I18N.MetaModellingGuidelines.Rules["VO (Inf)"] = "Verb object (Inf)";
Signavio.I18N.MetaModellingGuidelines.Rules["VOS"] = "Verb object";
Signavio.I18N.MetaModellingGuidelines.Rules["SDS"] = "State description";
Signavio.I18N.MetaModellingGuidelines.Rules["SDS (2 PV)"] = "State description (2 PV)";
Signavio.I18N.MetaModellingGuidelines.Rules["None"] = "Other";
Signavio.I18N.MetaModellingGuidelines.Rules["REST"] = "Other";

Signavio.I18N.MetaModellingGuidelines.Rules["CS"] = "Categorisation";
Signavio.I18N.MetaModellingGuidelines.Rules["DES"] = "Activity description";
Signavio.I18N.MetaModellingGuidelines.Rules["EQ"] = "Equation";
Signavio.I18N.MetaModellingGuidelines.Rules["PQ"] = "Participle question";
Signavio.I18N.MetaModellingGuidelines.Rules["IQ"] = "Infinitive question";
Signavio.I18N.MetaModellingGuidelines.Rules["DESQ"] = "Descriptive question";
Signavio.I18N.MetaModellingGuidelines.Rules["AQ"] = "Adjective question";
Signavio.I18N.MetaModellingGuidelines.Rules["VQ"] = "Verb question";
Signavio.I18N.MetaModellingGuidelines.Rules["NVQ"] = "No verb question";
Signavio.I18N.MetaModellingGuidelines.Rules["EMPTY"] = "empty";

Signavio.I18N.MetaModellingGuidelines.Rules["TS"] = "Temporal state";

Signavio.I18N.MetaModellingGuidelines.Rules["QUESTION_AND_ANSWERS"] = "Question with answers";
Signavio.I18N.MetaModellingGuidelines.Rules["ANSWERS"] = "Options only";
Signavio.I18N.MetaModellingGuidelines.Rules["QUESTION"] = "Gateway label only";

/*   - - - - - - - - - - - -  */
/*   Other Structural Checks  */
/*   - - - - - - - - - - - -  */
Signavio.I18N.MetaModellingGuidelines.Rules["CorrectSyntaxRule"] = "Usage of correct syntax";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_CorrectSyntaxRule"] = "Checks if all diagrams are modeled using correct syntax.";

Signavio.I18N.MetaModellingGuidelines.Rules["NoMultiMergeRule"] = "Absence of multi merges";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_NoMultiMergeRule"] = "Checks if the diagram is free of multi merges.";

Signavio.I18N.MetaModellingGuidelines.Rules["NoDeadlocksRule"] = "Absence of deadlocks";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_NoDeadlocksRule"] = "Checks if the diagram is free of deadlocks.";

Signavio.I18N.MetaModellingGuidelines.Rules["ConsistencyInSubprocessChecker"] = "Consistency between superprocesses and subprocesses";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_ConsistencyInSubprocessChecker"] = "Checks if errors, escalations and messages are consistent between super- and subprocesses.";

Signavio.I18N.MetaModellingGuidelines.Rules["ConsistencyEndStateChecker"] = "Test of subprocess end states";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_ConsistencyEndStateChecker"] = "Subprocesses with more than one non-Error end state must be followed by a gateway that tests the end state.";

Signavio.I18N.MetaModellingGuidelines.Rules["ConsistencyStartEndEventChecker"] = "Consistent usage of start and end events";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_ConsistencyStartEndEventChecker"] = "Checks if  start and end events are used in consistent combinations.";

Signavio.I18N.MetaModellingGuidelines.Rules["ExtendedPoolNumberChecker"] = "Usage of a restricted number of expanded pools";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_ExtendedPoolNumberChecker"] = "Checks if diagrams are modeled not using more than a maximum number of expanded pools.";

Signavio.I18N.MetaModellingGuidelines.Rules["MeaningfulGatewayChecker"] = "Usage of meaningful gateways";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_MeaningfulGatewayChecker"] = "Checks if all gateways have splitting or merging behavior.";

Signavio.I18N.MetaModellingGuidelines.Rules["SplitOrMergeGatewayChecker"] = "Absence of split and join behavior on one element";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_SplitOrMergeGatewayChecker"] = "Checks if all gateways are either splitting or merging.";

Signavio.I18N.MetaModellingGuidelines.Rules["ActivitiesInPoolChecker"] = "Usage of activities in pools";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_ActivitiesInPoolChecker"] = "Checks if every none-blackbox pool contains at least one activity.";

Signavio.I18N.MetaModellingGuidelines.Rules["StartEventChecker"] = "Usage of only one start event";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_StartEventChecker"] = "Checks if only one start event is used in a process or a subprocess.";

Signavio.I18N.MetaModellingGuidelines.Rules["ConsistencyConditionAndDefaultChecker"] = "Correct usage of conditional and default flows";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_ConsistencyConditionAndDefaultChecker"] = "Checks if conditional and default flows are only used if it is semantically correct.";

Signavio.I18N.MetaModellingGuidelines.Rules["TaskSequenceFlowChecker"] = "Absence of multiple sequence flows on activities";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_TaskSequenceFlowChecker"] = "Checks if all activities have only one incoming and/or outgoing sequence flow.";

Signavio.I18N.MetaModellingGuidelines.Rules["SendReceiveChecker"] = "Usage of message flows only on correct nodes";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_SendReceiveChecker"] = "Checks if all message flows are annotated to sender and receiver elements.";

Signavio.I18N.MetaModellingGuidelines.Rules["ConsistencySignalChecker"] = "Consistent usage of signals";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_ConsistencySignalChecker"] = "Checks if throwing and catching signal events are used consistently.";

Signavio.I18N.MetaModellingGuidelines.Rules["NoSubprocessCycleChecker"] = "Absence of subprocess relation cycles";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_NoSubprocessCycleChecker"] = "Checks if all collapsed subprocesses only link diagrams contained in lower process levels.";

Signavio.I18N.MetaModellingGuidelines.Rules["FlowNodesInPoolChecker"] = "Consistent usage of pools";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_FlowNodesInPoolChecker"] = "Checks if all events, activities and gateways reside within a pool.";

Signavio.I18N.MetaModellingGuidelines.Rules["MaxNumSequentialXORSplitChecker"] = "Usage of a restricted number of consecutive or-splits";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_MaxNumSequentialXORSplitChecker"] = "Checks if all chains of consecutive or-splits are compressed into sufficiently small fragments.";

Signavio.I18N.MetaModellingGuidelines.Rules["UnusedPoolChecker"] = "Message exchange between pools";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_UnusedPoolChecker"] = "Checks if each pool exchanges messages with other pools modeled in same diagram.";

Signavio.I18N.MetaModellingGuidelines.Rules["LoopChecker"] = "Absence of loops";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_LoopChecker"] = "Checks if all paths of the diagram are free from loops.";

Signavio.I18N.MetaModellingGuidelines.Rules["PoolsAndLanesIfCallActivityChecker"] = "Absence of pools, lanes and participants in subprocesses";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_PoolsAndLanesIfCallActivityChecker"] = "Checks if only as call activity referenced subprocesses contain pools, lanes and participants.";

Signavio.I18N.MetaModellingGuidelines.Rules["ActivityBeforeOrGatewayChecker"] = "Usage of activities before or-splits";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_ActivityBeforeOrGatewayChecker"] = "Checks if every or-split has an activity as predecessor.";

Signavio.I18N.MetaModellingGuidelines.Rules["ConsistencyEndStateChecker"] = "Test of subprocess end states";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_ConsistencyEndStateChecker"] = "Subprocesses with more than one non-Error end state must be followed by a gateway that tests the end state.";

/* Custom Rules in Structure Checks */
Signavio.I18N.MetaModellingGuidelines.Rules["silver25"] = "Usage of interrupting boundary error events";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_silver25"] = "All boundary error events have to interrupt their respective activities.";

Signavio.I18N.MetaModellingGuidelines.Rules["silverS16"] = "Usage of subprocess end states";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_silverS16"] = "If a subprocess is followed by a yes/no gateway, at least one end event of the subprocess should be labeled to match the gateway label.";


/*   - - - - - - -  */
/*   Layout Checks  */
/*   - - - - - - -  */
Signavio.I18N.MetaModellingGuidelines.Rules["ModelingOrientationChecker"] = "Definition of the correct modeling direction";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_ModelingOrientationChecker"] = "Checks if the diagram\'s modeling direction matches the configured modeling direction.";

Signavio.I18N.MetaModellingGuidelines.Rules["CanvasSizeChecker"] = "Usage of a restricted diagram size";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_CanvasSizeChecker"] = "Checks if the diagram's size is smaller than a certain maximum size.";

Signavio.I18N.MetaModellingGuidelines.Rules["MarginChecker"] = "Usage of sufficient distances between elements";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_MarginChecker"] = "Checks if a minimum distance between two elements is used in the diagrams.";

Signavio.I18N.MetaModellingGuidelines.Rules["ColorChecker"] = "Usage of specified colors";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_ColorChecker"] = "Checks if the default colors have not been changed in the diagram.";

Signavio.I18N.MetaModellingGuidelines.Rules["EdgeDirectionChecker"] = "Usage of the defined edge direction";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_EdgeDirectionChecker"] = "Checks if the direction of edges matches with the configured modeling orientation.";

Signavio.I18N.MetaModellingGuidelines.Rules["EdgeFoldingChecker"] = "Consistent edge folding";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_EdgeFoldingChecker"] = "Checks if the layout of edges is either straight or right-angled.";

Signavio.I18N.MetaModellingGuidelines.Rules["EdgeIntersectChecker"] = "Absence of edge overlays";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_EdgeIntersectChecker"] = "Checks if all edges run next to each other instead of overlaying each other.";

Signavio.I18N.MetaModellingGuidelines.Rules["NodeIntersectChecker"] = "Absence of node intersections";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_NodeIntersectChecker"] = "Checks if all nodes lie next to other elements instead of overlapping each other.";

Signavio.I18N.MetaModellingGuidelines.Rules["OutgoingEdgeDirectionChecker"] = "Consistent incoming and outgoing behavior of edges";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_OutgoingEdgeDirectionChecker"] = "Checks if the modeled edges behave as defined by the modeling direction on diagram level.";

Signavio.I18N.MetaModellingGuidelines.Rules["SizeChecker"] = "Usage of specified element sizes";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_SizeChecker"] = "Checks if the default element sizes have not been changed in the diagram.";

Signavio.I18N.MetaModellingGuidelines.Rules["MessageOnCanvasChecker"] = "Placing messages between pools";
Signavio.I18N.MetaModellingGuidelines.Rules["desc_MessageOnCanvasChecker"] = "Checks if all message objects are located between two pools.";

/*   - - -  */
/*   Misc   */
/*   - - -  */
Signavio.I18N.MetaModellingGuidelines.Rules["Language Switch"] = "Language switch";

Signavio.I18N.MetaModellingGuidelines.Rules["START_EVENT"] = "Start events";
Signavio.I18N.MetaModellingGuidelines.Rules["END_EVENT"] = "End events";
Signavio.I18N.MetaModellingGuidelines.Rules["INTERMEDIATE_EVENT"] = "Intermediate events";
Signavio.I18N.MetaModellingGuidelines.Rules["INTER_CATCHING"] = "Catching intermediate events";
Signavio.I18N.MetaModellingGuidelines.Rules["INTER_THROWING"] = "Throwing intermediate events";
Signavio.I18N.MetaModellingGuidelines.Rules["BOUNDARY_EVENT"] = "Boundary events";

Signavio.I18N.MetaModellingGuidelines.Rules["SequenceFlow"] = "Sequence flows";
Signavio.I18N.MetaModellingGuidelines.Rules["MessageFlow"] = "Message flows";
Signavio.I18N.MetaModellingGuidelines.Rules["ASSOCIATION"] = "Associations";

Signavio.I18N.MetaModellingGuidelines.Rules["ROLE"] = "Roles";
Signavio.I18N.MetaModellingGuidelines.Rules["DATA"] = "Data objects";
Signavio.I18N.MetaModellingGuidelines.Rules["POOL"] = "Pools";
Signavio.I18N.MetaModellingGuidelines.Rules["ACT"] = "Activities";
Signavio.I18N.MetaModellingGuidelines.Rules["EVT"] = "Events";
Signavio.I18N.MetaModellingGuidelines.Rules["GATEWAY"] = "Gateways";

Signavio.I18N.MetaModellingGuidelines.Rules["Roles"] = "Roles";
Signavio.I18N.MetaModellingGuidelines.Rules["DataObjects"] = "Data objects";
Signavio.I18N.MetaModellingGuidelines.Rules["Tasks"] = "Activities";
Signavio.I18N.MetaModellingGuidelines.Rules["Events"] = "Events";
Signavio.I18N.MetaModellingGuidelines.Rules["Gateways"] = "Gateways";

Signavio.I18N.MetaModellingGuidelines.Rules["AS_SUBPROCESS"] = "within subprocesses";
Signavio.I18N.MetaModellingGuidelines.Rules["AS_PARENT"] = "within processes";
Signavio.I18N.MetaModellingGuidelines.Rules["IN"] = "incoming";
Signavio.I18N.MetaModellingGuidelines.Rules["OUT"] = "outgoing";

Signavio.I18N.MetaModellingGuidelines.Rules["DEAD_LOCK"] = "Deadlock";
Signavio.I18N.MetaModellingGuidelines.Rules["MULTI_MERGE"] = "Multi merge";

Signavio.I18N.MetaModellingGuidelines.Rules["STRICT"] = "Strictly";
Signavio.I18N.MetaModellingGuidelines.Rules["ALLOW_ORTHOGONALS"] = "Perpendiculars allowed";

if (!Signavio.I18N.MetaModellingGuidelines.Dialog){ Signavio.I18N.MetaModellingGuidelines.Dialog = {}; }
Signavio.I18N.MetaModellingGuidelines.Dialog.title = "Modeling conventions";
Signavio.I18N.MetaModellingGuidelines.Dialog.description = "Please select diagrams or folders that will be checked for compliance regarding a specific modeling convention.";
Signavio.I18N.MetaModellingGuidelines.Dialog.select = "Please select a preconfigured modeling conventions.";
Signavio.I18N.MetaModellingGuidelines.Dialog.empty = "Select a convention";
Signavio.I18N.MetaModellingGuidelines.Dialog.configuration_overview = "Modeling conventions";


if (!Signavio.I18N.MetaModellingGuidelines.RulesName){ Signavio.I18N.MetaModellingGuidelines.RulesName = {}; }
Signavio.I18N.MetaModellingGuidelines.RulesName["eCH"] = "eCH-0158 BPMN conventions";
Signavio.I18N.MetaModellingGuidelines.RulesName["signaviobestpractice"] = "Signavio Best Practices";
Signavio.I18N.MetaModellingGuidelines.RulesName["bpmnmethodandstyle"] = "BPMN Method & Style conventions";

if(!Signavio.I18N.ModGuidelines) { Signavio.I18N.ModGuidelines = {}; }
Signavio.I18N.ModGuidelines.title = "Modeling conventions (XLSX)";
Signavio.I18N.ModGuidelines.errorTitle = "Modeling conventions (XLSX)";
Signavio.I18N.ModGuidelines.errorDesc = "There were no modeling conventions found. For questions please contact the Signavio Support";


/** MetaModellingGuidelines - Plugin END **/

if(!Signavio.I18N.Repository.AnalyticsLink) Signavio.I18N.Repository.AnalyticsLink = {};
Signavio.I18N.Repository.AnalyticsLink.name = "Analyze Cases";

//Deploy2Effekfif
if(!Signavio.I18N.Repository.Deploy2Effektif) {Signavio.I18N.Repository.Deploy2Effektif = {};}
Signavio.I18N.Repository.Deploy2Effektif.title = "Publish in Effektif";
Signavio.I18N.Repository.Deploy2Effektif.description = "Publish the selected process diagram to Effektif.";
Signavio.I18N.Repository.Deploy2Effektif.suc = "Publishing was successful. At <b>#{time}</b>, diagram revision <b>#{buildNr}</b> has been published.";
Signavio.I18N.Repository.Deploy2Effektif.fail = "Publishing to Effektif failed.";
Signavio.I18N.Repository.Deploy2Effektif.failConnect = "Publishing to Effektif failed. Could not connect to the Effektif server.";
Signavio.I18N.Repository.Deploy2Effektif.syntax = "The diagram could not be published in Effektif, as the model checking showed <b>syntax errors</b>.";
Signavio.I18N.Repository.Deploy2Effektif.stencilset = "The diagram cannot be published in Effektif, as it is no BPMN 2.0 process diagram.";
Signavio.I18N.Repository.Deploy2Effektif.transform = "The diagram could not be transformed. Please contact the support.";
Signavio.I18N.Repository.Deploy2Effektif.plausibility = "The diagram could not be published in Effektif, as the model checking showed <b>plausibility errors</b>.";
Signavio.I18N.Repository.Deploy2Effektif.checkFail2 = "Please open the <a href=\"#{link}\" target=\"_blank\">diagram with the editor</a> in order to fix the problems.";
Signavio.I18N.Repository.Deploy2Effektif.checkFail3 = "In the opened editor, errors can be visualized by clicking the <img src=\"#{image}\"> button.";
Signavio.I18N.Repository.Deploy2Effektif.deploy = "Publish";
Signavio.I18N.Repository.Deploy2Effektif.endPoint = "Endpoint";
Signavio.I18N.Repository.Deploy2Effektif.client = "Client";
Signavio.I18N.Repository.Deploy2Effektif.type = "Type";
Signavio.I18N.Repository.Deploy2Effektif.deployFail1 = "The diagram could not be published in Effektif due to the following errors:";
Signavio.I18N.Repository.Deploy2Effektif.deployFail2 = "The diagram could not be published in Effektif due to errors during transformation. The following diagram shows these errors: ";
Signavio.I18N.Repository.Deploy2Effektif.authentificationFailedTitle = "Authentification failed";
Signavio.I18N.Repository.Deploy2Effektif.authentificationFailedDescription = "The authentification went wrong. Please use a correct username/password combination.";
Signavio.I18N.Repository.Deploy2Effektif.progressTitle = "Effektif";
Signavio.I18N.Repository.Deploy2Effektif.progressDescription = "Publishing is in progress";
Signavio.I18N.Repository.Deploy2Effektif.version = "Version";
Signavio.I18N.Repository.Deploy2Effektif.versionNewTitle = "Save as a new version";
Signavio.I18N.Repository.Deploy2Effektif.versionNewDescription = "All running process instances are still work after the latest process, however new instances get startet with the new one.";
Signavio.I18N.Repository.Deploy2Effektif.versionCurTitle = "Overwrite current version";
Signavio.I18N.Repository.Deploy2Effektif.versionCurDescription = "With overwriting the current version all running and new instances are proceed with this process version.";

Signavio.I18N.Repository.Deploy2Effektif.deployedCurrentRevision = "This revision has been published in Effektif";
Signavio.I18N.Repository.Deploy2Effektif.deplozedOlderRevision = "An older revision has been published in Effektif";

if(!Signavio.I18N.Repository.Deploy2Effektif.ReturnCode) {Signavio.I18N.Repository.Deploy2Effektif.ReturnCode = {};}
Signavio.I18N.Repository.Deploy2Effektif.ReturnCode.CONNECTOR_COMMUNICATION_ERROR = "The server's internal communication failed.";
Signavio.I18N.Repository.Deploy2Effektif.ReturnCode.NO_MULTIPART_REQUEST = "The request was invalid.";
Signavio.I18N.Repository.Deploy2Effektif.ReturnCode.NO_PARAMETER_PARSED = "Request parameters were missing.";
Signavio.I18N.Repository.Deploy2Effektif.ReturnCode.AUTHENTIFICATION_FAILED = "Authentification failed.";
Signavio.I18N.Repository.Deploy2Effektif.ReturnCode.INTERNAL_SYSTEM_ERROR = "An internal server error occurred.";

Signavio.I18N.Repository.Offer.referenceModels = "Import reference models";
Signavio.I18N.Repository.Offer.referenceModelsDesc = "Through our partners Signavio offers several reference models that you can import into your workspace.";
Signavio.I18N.Repository.Offer.importReferenceModel = 'Import reference model "{referenceModelId}"';
Signavio.I18N.Repository.Offer.importReferenceModelDesc = 'Click "Import" to import the reference model <b>{referenceModelId}</b>. All contents are imported into the current folder. For further informationen please take a look at our user manual.';

