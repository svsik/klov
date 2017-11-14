<!-- navbar right -->
<ul class="nav navbar-nav pull-right">
	<#if project.isPresent()>
	<li class="nav-item">
		<a href="#" class="nav-link clear">
			<span class="label warn">
				${project.get().name}
			</span>
		</a>
	</li>
	</#if>
    <li class="nav-item dropdown pos-stc-xs">
        <a class="nav-link" data-toggle="dropdown">
        <i class="fa fa-search"></i>
        </a>
        <div class="dropdown-menu text-color w-md animated fadeInUp pull-right">
            <!-- search form -->
            <form class="navbar-form form-inline navbar-item m-a-0 p-x v-m" role="search">
                <div class="form-group l-h m-a-0">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search tests..">
                        <span class="input-group-btn">
                        <button type="submit" class="btn white b-a no-shadow"><i class="fa fa-search"></i></button>
                        </span>
                    </div>
                </div>
            </form>
            <!-- / search form -->
        </div>
    </li>
    <!-- dropdown -->
    <!-- 
        <li class="nav-item dropdown pos-stc-xs">
          <a class="nav-link clear" data-toggle="dropdown">
            <i class="ion-android-notifications-none w-24"></i>
            <span class="label up p-a-0 danger"></span>
          </a>
        div class="dropdown-menu pull-right w-xl animated fadeIn no-bg no-border no-shadow">
              <div class="scrollable" style="max-height: 220px">
                <ul class="list-group list-group-gap m-a-0">
                  <li class="list-group-item dark-white box-shadow-z0 b">
                    <span class="pull-left m-r">
                      <img src="images/a0.jpg" alt="..." class="w-40 img-circle">
                    </span>
                    <span class="clear block">
                      Update <a href="#" class="text-primary">1</a><br>
                      <small class="text-muted">10 minutes ago</small>
                    </span>
                  </li>
                  <li class="list-group-item dark-white box-shadow-z0 b">
                    <span class="pull-left m-r">
                      <img src="images/a1.jpg" alt="..." class="w-40 img-circle">
                    </span>
                    <span class="clear block">
                      <a href="#" class="text-primary">Update</a> 2<br>
                      <small class="text-muted">2 hours ago</small>
                    </span>
                  </li>
                  <li class="list-group-item dark-white text-color box-shadow-z0 b">
                    <span class="pull-left m-r">
                      <img src="images/a2.jpg" alt="..." class="w-40 img-circle">
                    </span>
                    <span class="clear block">
                      <a href="#" class="text-primary">Update</a> 3<br>
                      <small class="text-muted">1 day ago</small>
                    </span>
                  </li>
                </ul>
              </div>
          </div>
        </li>
         -->
    <#if projectList??>
    <li class="nav-item dropdown" ng-controller="ProjectController">
        <a href="#" class="nav-link clear" data-toggle="dropdown">
        <span class="w-32 avatar transparent">
        <span class="filler"></span>
        <small><i class="fa fa-briefcase"></i></small>
        </span>
        </a>
        <div class="dropdown-menu w dropdown-menu-scale pull-right">
            <#list projectList as project>
            <a class="dropdown-item" href="#" ng-click="assign('${project.name}')">
            <span>${project.name}</span>
            </a>
            </#list>
            <div class="dropdown divider"></div>
            <a class="dropdown-item" ng-click="reset()">
            <span>Reset</span>
            </a>
        </div>
    </li>
    </#if>
    <!-- / dropdown -->
    <#if user??>
    <li class="nav-item dropdown">
        <a href="#" class="nav-link clear" data-toggle="dropdown">
        <span class="w-32 avatar purple">
        <span class="filler"></span>
        <span>${user.name[0]?capitalize}</span>
        <i class="on b-white bottom"></i>
        </span>
        </a>
        <div class="dropdown-menu w dropdown-menu-scale pull-right">
            <a class="dropdown-item" href="/account/settings">
            <span>Account</span>
            </a>
            <div class="dropdown divider"></div>
            <a class="dropdown-item" href="/auth/signout">Sign out</a>
        </div>
    </li>
    <#else>
    <li class="nav-item dropdown">
        <a href="#" class="nav-link clear" data-toggle="dropdown">
        <span class="w-32 avatar purple">
        <span class="filler"></span>
        <span>k.</span>
        </span>
        </a>
        <div class="dropdown-menu w dropdown-menu-scale pull-right">
            <a class="dropdown-item" href="/auth/signon">
            <span>Sign On</span>
            </a>
        </div>
    </li>
    </#if>
</ul>
<!-- / navbar right -->