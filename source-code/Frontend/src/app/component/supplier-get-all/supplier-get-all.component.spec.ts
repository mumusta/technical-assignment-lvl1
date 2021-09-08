import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplierGetAllComponent } from './supplier-get-all.component';

describe('SupplierGetAllComponent', () => {
  let component: SupplierGetAllComponent;
  let fixture: ComponentFixture<SupplierGetAllComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SupplierGetAllComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SupplierGetAllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
