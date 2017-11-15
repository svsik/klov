<div id="aside" class="app-aside fade nav-dropdown black">
    <!-- fluid app aside -->
    <div class="navside dk" data-layout="column">
        <div class="navbar no-radius">
            <!-- brand -->
            <#include 'brand.ftl'>
            <!-- / brand -->
        </div>
        <div data-flex class="hide-scroll">
            <nav class="scroll nav-stacked nav-stacked-rounded nav-color">
                <ul class="nav" data-ui-nav>
                    <li class="nav-header hidden-folded">
                        <span class="text-xs">Main</span>
                    </li>
                    <li>
                        <a href="/builds" class="no-ajax" alt="Builds" title="Builds">
                        <span class="nav-icon no-fade">
                        <i class="material-icons">dns</i>
                        </span>
                        <span class="nav-text">Reports</span>
                        </a>
                    </li>
                    <li>
                        <a href="/dashboard" class="no-ajax" alt="Dashboard" title="Dashboard">
                        <span class="nav-icon no-fade">
                        <i class="material-icons">dashboard</i>
                        </span>
                        <span class="nav-text">Dashboard</span>
                        </a>
                    </li>
                    <#if extentApiVersion=="pro">
                    <li>
                        <a href="/tags" class="no-ajax" alt="Dashboard" title="Dashboard">
                        <span class="nav-icon no-fade">
                        <i class="material-icons">local_offer</i>
                        </span>
                        <span class="nav-text">Tags</span>
                        </a>
                    </li>
                    </#if>
                </ul>
            </nav>
        </div>
        <#include 'profile.ftl'>
    </div>
</div>