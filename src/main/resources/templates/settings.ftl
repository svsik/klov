<!DOCTYPE html>
<html lang="en" ng-app="Klov">
	<#include 'partials/head.ftl'>
	<body>
		<div class="app" id="app">
			<#include 'partials/sidenav.ftl'>
			<!-- content -->
			<div id="content" class="app-content box-shadow-z2 bg pjax-container" role="main">
				<div class="app-header white bg b-b">
					<div class="navbar" data-pjax>
						<a data-toggle="modal" data-target="#aside" class="navbar-item pull-left hidden-lg-up p-r m-a-0">
						<i class="material-icons">reorder</i>
						</a>
						<div class="navbar-item pull-left h5" id="pageTitle">Account Settings</div>
						<#include 'partials/navbar-right.ftl'>
					</div>
				</div>
				<#include 'partials/footer.ftl'>
				<div class="app-body">
					<!-- ############ PAGE START-->
					<div class="row-col">
						<div class="col-sm-3 col-lg-2 b-r">
							<div class="p-y">
								<div class="nav-active-border left b-primary">
									<ul class="nav nav-sm">
										<li class="nav-item">
											<a class="nav-link block active" href="#" data-toggle="tab" data-target="#tab-1">Profile</a>
										</li>
										<li class="nav-item">
											<a class="nav-link block" href="/account/settings/security">Security</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
						<div class="col-sm-9 col-lg-10 light bg">
							<div class="tab-content pos-rlt">
								<div class="tab-pane active" id="tab-1">
									<div class="p-a-md b-b _600">Profile</div>
									<form role="form" class="p-a-md col-md-6">
										<div class="form-group">
											<label>Profile picture</label>
											<div class="form-file">
												<input type="file">
												<button class="btn white">Upload new picture</button>
											</div>
										</div>
										<div class="form-group">
											<label>First Name</label>
											<input type="text" class="form-control">
										</div>
										<div class="form-group">
											<label>Last Name</label>
											<input type="text" class="form-control">
										</div>
										<div class="form-group">
											<label>URL</label>
											<input type="text" class="form-control">
										</div>
										<div class="form-group">
											<label>Company</label>
											<input type="text" class="form-control">
										</div>
										<div class="form-group">
											<label>Location</label>
											<input type="text" class="form-control">
										</div>
										<button type="submit" class="btn btn-info m-t">Update</button>
									</form>
								</div>
								<div class="tab-pane" id="tab-2">
									<div class="p-a-md b-b _600">Security</div>
									<div class="p-a-md">
										<div class="clearfix m-b-lg">
											<form role="form" class="col-md-6 p-a-0" action="/auth/changePassword">
												<div class="form-group">
													<label>Old Password</label>
													<input type="password" class="form-control" name="current">
												</div>
												<div class="form-group">
													<label>New Password</label>
													<input type="password" class="form-control" name="updated">
												</div>
												<div class="form-group">
													<label>New Password Again</label>
													<input type="password" class="form-control" name="updatedMatch">
												</div>
												<button type="submit" class="btn btn-info m-t">Update</button>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- ############ PAGE END-->
				</div>
			</div>
			<!-- / -->
			<#include 'partials/switcher.ftl'>
			<!-- ############ LAYOUT END-->
		</div>
		<#include 'partials/scripts.ftl'>
	</body>
</html>