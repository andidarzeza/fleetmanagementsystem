import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicledetailsComponent } from './vehicledetails.component';

describe('VehicledetailsComponent', () => {
  let component: VehicledetailsComponent;
  let fixture: ComponentFixture<VehicledetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehicledetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehicledetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
