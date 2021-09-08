import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplierGetOneComponent } from './supplier-get-one.component';

describe('SupplierGetOneComponent', () => {
  let component: SupplierGetOneComponent;
  let fixture: ComponentFixture<SupplierGetOneComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SupplierGetOneComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SupplierGetOneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
