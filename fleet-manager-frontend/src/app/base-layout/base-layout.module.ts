import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LayoutComponent } from "./layout.component";
import { MaterialModule } from "../material.module";
import { FuseProgressBarModule, FuseSidebarModule, FuseThemeOptionsModule } from "@fuse/components";
import { VerticalLayout1Module } from "./vertical/layout-1/layout-1.module";
import { VerticalLayout2Module } from "./vertical/layout-2/layout-2.module";
import { VerticalLayout3Module } from "./vertical/layout-3/layout-3.module";
import { HorizontalLayout1Module } from "./horizontal/layout-1/layout-1.module";
import { NotificationsComponent } from './components/notifications/notifications.component';
import { TimeDisplayerModule } from './components/time-displayer/time-displayer.module';
import { LoginComponent} from "./components/login/login.component";
import {FlexModule} from "@angular/flex-layout";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { RegisterComponent } from './components/register/register.component';
import {RouterModule, Routes} from "@angular/router";
import { AdminComponent } from './components/admin/admin.component';
import { UserPanelComponent } from './components/user-panel/user-panel.component';
import { CompanyDetailsComponent } from './components/company-details/company-details.component';
import { CompaniesComponent } from './components/companies/companies.component';
import { ConfirmDialogComponent } from './components/confirm-dialog/confirm-dialog.component';
import { VehiclesComponent } from './components/vehicles/vehicles.component';
import { DriversComponent } from './components/drivers/drivers.component';
import { VehicledetailsComponent } from './components/vehicledetails/vehicledetails.component';
import { HistoryComponent } from './components/history/history.component';
import {DragDropModule} from "@angular/cdk/drag-drop";
import { MapComponent } from './components/map/map.component';

@NgModule({
    declarations: [
        LayoutComponent,
         NotificationsComponent,
         LoginComponent,
         RegisterComponent,
         AdminComponent,
         UserPanelComponent,
         CompanyDetailsComponent,
         CompaniesComponent,
         ConfirmDialogComponent,
         VehiclesComponent,
         DriversComponent,
         VehicledetailsComponent,
         HistoryComponent,
         MapComponent
    ],
    imports: [
        CommonModule,
        MaterialModule,
        FuseThemeOptionsModule,
        VerticalLayout1Module,
        VerticalLayout2Module,
        VerticalLayout3Module,
        HorizontalLayout1Module,
        FuseProgressBarModule,
        FuseSidebarModule,
        TimeDisplayerModule,
        FlexModule,
        FormsModule,
        RouterModule,
        ReactiveFormsModule,
        DragDropModule
    ],
    exports: [
        LayoutComponent,
        FuseThemeOptionsModule,
        VerticalLayout1Module,
        VerticalLayout2Module,
        VerticalLayout3Module,
        HorizontalLayout1Module,
        FuseProgressBarModule,
        FuseSidebarModule,
        TimeDisplayerModule
    ],
    entryComponents: [
        NotificationsComponent
    ]
})
export class BaseLayoutModule {
}
