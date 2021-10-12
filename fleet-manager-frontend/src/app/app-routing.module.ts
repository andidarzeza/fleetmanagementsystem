import {ExtraOptions, RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {LoginComponent} from "./base-layout/components/login/login.component";
import {RegisterComponent} from "./base-layout/components/register/register.component";
import {UserPanelComponent} from "./base-layout/components/user-panel/user-panel.component";
import {CompanyDetailsComponent} from "./base-layout/components/company-details/company-details.component";
import {CompaniesComponent} from "./base-layout/components/companies/companies.component";
import {AuthGuard} from "./base-layout/services/auth.guard";
import {VehiclesComponent} from "./base-layout/components/vehicles/vehicles.component";
import {DriversComponent} from "./base-layout/components/drivers/drivers.component";
import {HistoryComponent} from "./base-layout/components/history/history.component";
import {MapComponent} from "./base-layout/components/map/map.component";


const routes: Routes = [
    { path: '', redirectTo:"/login", pathMatch: 'full'},
    { path: "register", component: RegisterComponent},
    { path: "login",component: LoginComponent},
    { path: "panel",component: UserPanelComponent, canActivate:[AuthGuard]},
    { path: "companies",component: CompaniesComponent, canActivate:[AuthGuard]},
    { path: "drivers/:id",component: DriversComponent, canActivate:[AuthGuard]},
    {path:"companies/:id",component: CompanyDetailsComponent, canActivate:[AuthGuard]},
    {path: "vehicles/:id",component: VehiclesComponent, canActivate:[AuthGuard]},
    {path:"history",component: HistoryComponent,canActivate: [AuthGuard]},
    {path:"map",component: MapComponent,canActivate: [AuthGuard]}
];

// const config: ExtraOptions = {
//     useHash: true,
// };


//imports: [RouterModule.forRoot(routes, config)],

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {

}
